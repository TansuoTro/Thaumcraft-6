/*     */ package thaumcraft.api.research.theorycraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResearchTableData
/*     */ {
/*     */   public TileEntity table;
/*     */   public String player;
/*     */   public int inspiration;
/*     */   public int inspirationStart;
/*     */   public int bonusDraws;
/*     */   public int placedCards;
/*     */   public int aidsChosen;
/*     */   public int penaltyStart;
/*     */   public ArrayList<Long> savedCards;
/*     */   public ArrayList<String> aidCards;
/*     */   public TreeMap<String, Integer> categoryTotals;
/*     */   public ArrayList<String> categoriesBlocked;
/*     */   public ArrayList<CardChoice> cardChoices;
/*     */   public CardChoice lastDraw;
/*     */   
/*     */   public class CardChoice
/*     */   {
/*     */     public TheorycraftCard card;
/*     */     public String key;
/*     */     public boolean fromAid;
/*     */     public boolean selected;
/*     */     
/*     */     public CardChoice(String key, TheorycraftCard card, boolean aid, boolean selected) {
/*  45 */       this.key = key;
/*  46 */       this.card = card;
/*  47 */       this.fromAid = aid;
/*  48 */       this.selected = selected;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  53 */       return "key:" + this.key + " card:" + this.card
/*  54 */         .getSeed() + " fromAid:" + this.fromAid + " selected:" + this.selected;
/*     */     }
/*     */   } public ResearchTableData(TileEntity tileResearchTable) { this.savedCards = new ArrayList();
/*     */     this.aidCards = new ArrayList();
/*     */     this.categoryTotals = new TreeMap();
/*     */     this.categoriesBlocked = new ArrayList();
/*     */     this.cardChoices = new ArrayList();
/*  61 */     this.table = tileResearchTable; } public ResearchTableData(EntityPlayer player2, TileEntity tileResearchTable) { this.savedCards = new ArrayList(); this.aidCards = new ArrayList();
/*     */     this.categoryTotals = new TreeMap();
/*     */     this.categoriesBlocked = new ArrayList();
/*     */     this.cardChoices = new ArrayList();
/*  65 */     this.player = player2.func_70005_c_();
/*  66 */     this.table = tileResearchTable; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public boolean isComplete() { return (this.inspiration <= 0); }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public boolean hasTotal(String cat) { return this.categoryTotals.containsKey(cat); }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public int getTotal(String cat) { return this.categoryTotals.containsKey(cat) ? ((Integer)this.categoryTotals.get(cat)).intValue() : 0; }
/*     */ 
/*     */   
/*     */   public void addTotal(String cat, int amt) {
/*  82 */     int current = this.categoryTotals.containsKey(cat) ? ((Integer)this.categoryTotals.get(cat)).intValue() : 0;
/*  83 */     current += amt;
/*  84 */     if (current <= 0) {
/*  85 */       this.categoryTotals.remove(cat);
/*     */     } else {
/*  87 */       this.categoryTotals.put(cat, Integer.valueOf(current));
/*     */     } 
/*     */   }
/*     */   public void addInspiration(int amt) {
/*  91 */     this.inspiration += amt;
/*  92 */     if (this.inspiration > this.inspirationStart) this.inspiration = this.inspirationStart; 
/*     */   }
/*     */   
/*     */   public NBTTagCompound serialize() {
/*  96 */     NBTTagCompound nbt = new NBTTagCompound();
/*     */     
/*  98 */     nbt.func_74778_a("player", this.player);
/*  99 */     nbt.func_74768_a("inspiration", this.inspiration);
/* 100 */     nbt.func_74768_a("inspirationStart", this.inspirationStart);
/* 101 */     nbt.func_74768_a("placedCards", this.placedCards);
/* 102 */     nbt.func_74768_a("bonusDraws", this.bonusDraws);
/* 103 */     nbt.func_74768_a("aidsChosen", this.aidsChosen);
/* 104 */     nbt.func_74768_a("penaltyStart", this.penaltyStart);
/*     */ 
/*     */     
/* 107 */     NBTTagList savedTag = new NBTTagList();
/* 108 */     for (Long card : this.savedCards) {
/* 109 */       NBTTagCompound gt = new NBTTagCompound();
/* 110 */       gt.func_74772_a("card", card.longValue());
/* 111 */       savedTag.func_74742_a(gt);
/*     */     } 
/* 113 */     nbt.func_74782_a("savedCards", savedTag);
/*     */ 
/*     */     
/* 116 */     NBTTagList categoriesBlockedTag = new NBTTagList();
/* 117 */     for (String category : this.categoriesBlocked) {
/* 118 */       NBTTagCompound gt = new NBTTagCompound();
/* 119 */       gt.func_74778_a("category", category);
/* 120 */       categoriesBlockedTag.func_74742_a(gt);
/*     */     } 
/* 122 */     nbt.func_74782_a("categoriesBlocked", categoriesBlockedTag);
/*     */ 
/*     */     
/* 125 */     NBTTagList categoryTotalsTag = new NBTTagList();
/* 126 */     for (String category : this.categoryTotals.keySet()) {
/* 127 */       NBTTagCompound gt = new NBTTagCompound();
/* 128 */       gt.func_74778_a("category", category);
/* 129 */       gt.func_74768_a("total", ((Integer)this.categoryTotals.get(category)).intValue());
/* 130 */       categoryTotalsTag.func_74742_a(gt);
/*     */     } 
/* 132 */     nbt.func_74782_a("categoryTotals", categoryTotalsTag);
/*     */ 
/*     */     
/* 135 */     NBTTagList aidCardsTag = new NBTTagList();
/* 136 */     for (String mc : this.aidCards) {
/* 137 */       NBTTagCompound gt = new NBTTagCompound();
/* 138 */       gt.func_74778_a("aidCard", mc);
/* 139 */       aidCardsTag.func_74742_a(gt);
/*     */     } 
/* 141 */     nbt.func_74782_a("aidCards", aidCardsTag);
/*     */ 
/*     */     
/* 144 */     NBTTagList cardChoicesTag = new NBTTagList();
/* 145 */     for (CardChoice mc : this.cardChoices) {
/* 146 */       NBTTagCompound gt = serializeCardChoice(mc);
/* 147 */       cardChoicesTag.func_74742_a(gt);
/*     */     } 
/* 149 */     nbt.func_74782_a("cardChoices", cardChoicesTag);
/*     */     
/* 151 */     if (this.lastDraw != null) nbt.func_74782_a("lastDraw", serializeCardChoice(this.lastDraw));
/*     */     
/* 153 */     return nbt;
/*     */   }
/*     */   
/*     */   public NBTTagCompound serializeCardChoice(CardChoice mc) {
/* 157 */     NBTTagCompound nbt = new NBTTagCompound();
/* 158 */     nbt.func_74778_a("cardChoice", mc.key);
/* 159 */     nbt.func_74757_a("aid", mc.fromAid);
/* 160 */     nbt.func_74757_a("select", mc.selected);
/*     */     try {
/* 162 */       nbt.func_74782_a("cardNBT", mc.card.serialize());
/* 163 */     } catch (Exception exception) {}
/* 164 */     return nbt;
/*     */   }
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/* 168 */     if (nbt == null)
/* 169 */       return;  this.inspiration = nbt.func_74762_e("inspiration");
/* 170 */     this.inspirationStart = nbt.func_74762_e("inspirationStart");
/* 171 */     this.placedCards = nbt.func_74762_e("placedCards");
/* 172 */     this.bonusDraws = nbt.func_74762_e("bonusDraws");
/* 173 */     this.aidsChosen = nbt.func_74762_e("aidsChosen");
/* 174 */     this.penaltyStart = nbt.func_74762_e("penaltyStart");
/* 175 */     this.player = nbt.func_74779_i("player");
/*     */ 
/*     */     
/* 178 */     NBTTagList savedTag = nbt.func_150295_c("savedCards", 10);
/* 179 */     this.savedCards = new ArrayList();
/* 180 */     for (int x = 0; x < savedTag.func_74745_c(); x++) {
/* 181 */       NBTTagCompound nbtdata = savedTag.func_150305_b(x);
/* 182 */       this.savedCards.add(Long.valueOf(nbtdata.func_74763_f("card")));
/*     */     } 
/*     */ 
/*     */     
/* 186 */     NBTTagList categoriesBlockedTag = nbt.func_150295_c("categoriesBlocked", 10);
/* 187 */     this.categoriesBlocked = new ArrayList();
/* 188 */     for (int x = 0; x < categoriesBlockedTag.func_74745_c(); x++) {
/* 189 */       NBTTagCompound nbtdata = categoriesBlockedTag.func_150305_b(x);
/* 190 */       this.categoriesBlocked.add(nbtdata.func_74779_i("category"));
/*     */     } 
/*     */ 
/*     */     
/* 194 */     NBTTagList categoryTotalsTag = nbt.func_150295_c("categoryTotals", 10);
/* 195 */     this.categoryTotals = new TreeMap();
/* 196 */     for (int x = 0; x < categoryTotalsTag.func_74745_c(); x++) {
/* 197 */       NBTTagCompound nbtdata = categoryTotalsTag.func_150305_b(x);
/* 198 */       this.categoryTotals.put(nbtdata.func_74779_i("category"), Integer.valueOf(nbtdata.func_74762_e("total")));
/*     */     } 
/*     */ 
/*     */     
/* 202 */     NBTTagList aidCardsTag = nbt.func_150295_c("aidCards", 10);
/* 203 */     this.aidCards = new ArrayList();
/* 204 */     for (int x = 0; x < aidCardsTag.func_74745_c(); x++) {
/* 205 */       NBTTagCompound nbtdata = aidCardsTag.func_150305_b(x);
/* 206 */       this.aidCards.add(nbtdata.func_74779_i("aidCard"));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 211 */     EntityPlayer pe = null;
/* 212 */     if (this.table != null && this.table.func_145831_w() != null && !(this.table.func_145831_w()).field_72995_K) {
/* 213 */       pe = this.table.func_145831_w().func_72924_a(this.player);
/*     */     }
/* 215 */     NBTTagList cardChoicesTag = nbt.func_150295_c("cardChoices", 10);
/* 216 */     this.cardChoices = new ArrayList();
/* 217 */     for (int x = 0; x < cardChoicesTag.func_74745_c(); x++) {
/* 218 */       NBTTagCompound nbtdata = cardChoicesTag.func_150305_b(x);
/* 219 */       CardChoice cc = deserializeCardChoice(nbtdata);
/* 220 */       if (cc != null) this.cardChoices.add(cc);
/*     */     
/*     */     } 
/* 223 */     this.lastDraw = deserializeCardChoice(nbt.func_74775_l("lastDraw"));
/*     */   }
/*     */ 
/*     */   
/*     */   public CardChoice deserializeCardChoice(NBTTagCompound nbt) {
/* 228 */     if (nbt == null) return null; 
/* 229 */     String key = nbt.func_74779_i("cardChoice");
/* 230 */     TheorycraftCard tc = generateCardWithNBT(nbt.func_74779_i("cardChoice"), nbt.func_74775_l("cardNBT"));
/* 231 */     if (tc == null) return null; 
/* 232 */     return new CardChoice(key, tc, nbt.func_74767_n("aid"), nbt.func_74767_n("select"));
/*     */   }
/*     */ 
/*     */   
/* 236 */   private boolean isCategoryBlocked(String cat) { return this.categoriesBlocked.contains(cat); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawCards(int draw, EntityPlayer pe) {
/* 241 */     if (draw == 3) {
/* 242 */       if (this.bonusDraws > 0) {
/* 243 */         this.bonusDraws--;
/*     */       } else {
/* 245 */         draw = 2;
/*     */       } 
/*     */     }
/* 248 */     this.cardChoices.clear();
/* 249 */     this.player = pe.func_70005_c_();
/* 250 */     ArrayList<String> availCats = getAvailableCategories(pe);
/* 251 */     ArrayList<String> drawnCards = new ArrayList<String>();
/* 252 */     boolean aidDrawn = false;
/* 253 */     int failsafe = 0;
/* 254 */     while (draw > 0 && failsafe < 10000) {
/* 255 */       failsafe++;
/* 256 */       if (!aidDrawn && !this.aidCards.isEmpty() && pe.func_70681_au().nextFloat() <= 0.25D) {
/* 257 */         int idx = pe.func_70681_au().nextInt(this.aidCards.size());
/* 258 */         String key = (String)this.aidCards.get(idx);
/* 259 */         TheorycraftCard card = generateCard(key, -1L, pe);
/* 260 */         if (card == null || card.getInspirationCost() > this.inspiration || isCategoryBlocked(card.getResearchCategory()))
/*     */           continue; 
/* 262 */         if (drawnCards.contains(key))
/* 263 */           continue;  drawnCards.add(key);
/* 264 */         this.cardChoices.add(new CardChoice(key, card, true, false));
/* 265 */         this.aidCards.remove(idx);
/*     */       } else {
/*     */         try {
/* 268 */           String[] cards = (String[])TheorycraftManager.cards.keySet().toArray(new String[0]);
/* 269 */           int idx = pe.func_70681_au().nextInt(cards.length);
/* 270 */           TheorycraftCard card = generateCard(cards[idx], -1L, pe);
/* 271 */           if (card == null || card.isAidOnly() || card.getInspirationCost() > this.inspiration)
/* 272 */             continue;  if (card.getResearchCategory() != null) {
/* 273 */             boolean found = false;
/* 274 */             for (String cn : availCats) {
/* 275 */               if (cn.equals(card.getResearchCategory())) {
/* 276 */                 found = true;
/*     */                 break;
/*     */               } 
/*     */             } 
/* 280 */             if (!found)
/*     */               continue; 
/*     */           } 
/* 283 */           if (drawnCards.contains(cards[idx]))
/* 284 */             continue;  drawnCards.add(cards[idx]);
/* 285 */           this.cardChoices.add(new CardChoice(cards[idx], card, false, false));
/* 286 */         } catch (Exception e) {
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       
/* 291 */       draw--;
/*     */     } 
/*     */   }
/*     */   
/*     */   private TheorycraftCard generateCard(String key, long seed, EntityPlayer pe) {
/* 296 */     if (key == null) return null; 
/* 297 */     Class<TheorycraftCard> tcc = (Class)TheorycraftManager.cards.get(key);
/* 298 */     if (tcc == null) return null; 
/* 299 */     TheorycraftCard tc = null;
/*     */     try {
/* 301 */       tc = (TheorycraftCard)tcc.newInstance();
/* 302 */       if (seed < 0L)
/* 303 */       { if (pe != null) {
/* 304 */           tc.setSeed(pe.func_70681_au().nextLong());
/*     */         } else {
/* 306 */           tc.setSeed(System.nanoTime());
/*     */         }  }
/* 308 */       else { tc.setSeed(seed); }
/* 309 */        if (pe != null && !tc.initialize(pe, this)) return null; 
/* 310 */     } catch (Exception exception) {}
/* 311 */     return tc;
/*     */   }
/*     */   
/*     */   private TheorycraftCard generateCardWithNBT(String key, NBTTagCompound nbt) {
/* 315 */     if (key == null) return null; 
/* 316 */     Class<TheorycraftCard> tcc = (Class)TheorycraftManager.cards.get(key);
/* 317 */     if (tcc == null) return null; 
/* 318 */     TheorycraftCard tc = null;
/*     */     try {
/* 320 */       tc = (TheorycraftCard)tcc.newInstance();
/* 321 */       tc.deserialize(nbt);
/* 322 */     } catch (Exception exception) {}
/* 323 */     return tc;
/*     */   }
/*     */   
/*     */   public void initialize(EntityPlayer player1, Set<String> aids) {
/* 327 */     this.inspirationStart = getAvailableInspiration(player1);
/* 328 */     this.inspiration = this.inspirationStart - aids.size();
/*     */     
/* 330 */     for (String muk : aids) {
/* 331 */       ITheorycraftAid mu = (ITheorycraftAid)TheorycraftManager.aids.get(muk);
/* 332 */       if (mu != null) {
/* 333 */         for (Class clazz : mu.getCards()) {
/* 334 */           this.aidCards.add(clazz.getName());
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getAvailableCategories(EntityPlayer player) {
/* 342 */     ArrayList<String> cats = new ArrayList<String>();
/* 343 */     for (String rck : ResearchCategories.researchCategories.keySet()) {
/* 344 */       ResearchCategory rc = ResearchCategories.getResearchCategory(rck);
/* 345 */       if (rc != null && !isCategoryBlocked(rck) && (
/* 346 */         rc.researchKey == null || ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { rc.researchKey }))) {
/* 347 */         cats.add(rck);
/*     */       }
/*     */     } 
/* 350 */     return cats;
/*     */   }
/*     */   
/*     */   public static int getAvailableInspiration(EntityPlayer player) {
/* 354 */     float tot = 5.0F;
/* 355 */     IPlayerKnowledge knowledge = ThaumcraftCapabilities.getKnowledge(player);
/* 356 */     for (String s : knowledge.getResearchList()) {
/* 357 */       if (ThaumcraftCapabilities.knowsResearchStrict(player, new String[] { s })) {
/* 358 */         ResearchEntry re = ResearchCategories.getResearch(s);
/* 359 */         if (re == null)
/* 360 */           continue;  if (re.hasMeta(ResearchEntry.EnumResearchMeta.SPIKY))
/* 361 */           tot += 0.5F; 
/* 362 */         if (re.hasMeta(ResearchEntry.EnumResearchMeta.HIDDEN))
/* 363 */           tot += 0.1F; 
/*     */       } 
/*     */     } 
/* 366 */     return Math.min(15, Math.round(tot));
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\theorycraft\ResearchTableData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */