/*     */ package thaumcraft.api.aspects;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import org.apache.commons.lang3.text.WordUtils;
/*     */ import thaumcraft.api.research.ScanAspect;
/*     */ import thaumcraft.api.research.ScanningManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Aspect
/*     */ {
/*     */   String tag;
/*     */   Aspect[] components;
/*     */   int color;
/*     */   private String chatcolor;
/*     */   ResourceLocation image;
/*     */   int blend;
/*  28 */   public static HashMap<Integer, Aspect> mixList = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Aspect(String tag, int color, Aspect[] components, ResourceLocation image, int blend) {
/*  39 */     if (aspects.containsKey(tag)) throw new IllegalArgumentException(tag + " already registered!"); 
/*  40 */     this.tag = tag;
/*  41 */     this.components = components;
/*  42 */     this.color = color;
/*  43 */     this.image = image;
/*  44 */     this.blend = blend;
/*  45 */     aspects.put(tag, this);
/*  46 */     ScanningManager.addScannableThing(new ScanAspect("!" + tag, this));
/*  47 */     if (components != null) {
/*  48 */       int h = (components[0].getTag() + components[1].getTag()).hashCode();
/*  49 */       mixList.put(Integer.valueOf(h), this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public Aspect(String tag, int color, Aspect[] components) { this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), 1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public Aspect(String tag, int color, Aspect[] components, int blend) { this(tag, color, components, new ResourceLocation("thaumcraft", "textures/aspects/" + tag.toLowerCase() + ".png"), blend); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Aspect(String tag, int color, String chatcolor, int blend) {
/*  72 */     this(tag, color, (Aspect[])null, blend);
/*  73 */     setChatcolor(chatcolor);
/*     */   }
/*     */ 
/*     */   
/*  77 */   public int getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public String getName() { return WordUtils.capitalizeFully(this.tag); }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public String getLocalizedDescription() { return I18n.func_74838_a("tc.aspect." + this.tag); }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public String getTag() { return this.tag; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setTag(String tag) { this.tag = tag; }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public Aspect[] getComponents() { return this.components; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public void setComponents(Aspect[] components) { this.components = components; }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public ResourceLocation getImage() { return this.image; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static Aspect getAspect(String tag) { return (Aspect)aspects.get(tag); }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public int getBlend() { return this.blend; }
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void setBlend(int blend) { this.blend = blend; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public boolean isPrimal() { return (getComponents() == null || getComponents().length != 2); }
/*     */ 
/*     */ 
/*     */   
/* 125 */   private static ArrayList<Aspect> primals = new ArrayList();
/*     */   public static ArrayList<Aspect> getPrimalAspects() {
/* 127 */     if (primals.isEmpty()) {
/* 128 */       pa = aspects.values();
/* 129 */       for (Aspect aspect : pa) {
/* 130 */         if (aspect.isPrimal()) primals.add(aspect); 
/*     */       } 
/*     */     } 
/* 133 */     return primals;
/*     */   }
/*     */   
/* 136 */   private static ArrayList<Aspect> compounds = new ArrayList();
/*     */   public static ArrayList<Aspect> getCompoundAspects() {
/* 138 */     if (compounds.isEmpty()) {
/* 139 */       pa = aspects.values();
/* 140 */       for (Aspect aspect : pa) {
/* 141 */         if (!aspect.isPrimal()) compounds.add(aspect); 
/*     */       } 
/*     */     } 
/* 144 */     return compounds;
/*     */   }
/*     */ 
/*     */   
/* 148 */   public String getChatcolor() { return this.chatcolor; }
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setChatcolor(String chatcolor) { this.chatcolor = chatcolor; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 157 */   public static LinkedHashMap<String, Aspect> aspects = new LinkedHashMap();
/*     */ 
/*     */   
/* 160 */   public static final Aspect AIR = new Aspect("aer", 16777086, "e", 1);
/* 161 */   public static final Aspect EARTH = new Aspect("terra", 5685248, "2", 1);
/* 162 */   public static final Aspect FIRE = new Aspect("ignis", 16734721, "c", 1);
/* 163 */   public static final Aspect WATER = new Aspect("aqua", 3986684, "3", 1);
/* 164 */   public static final Aspect ORDER = new Aspect("ordo", 14013676, "7", 1);
/* 165 */   public static final Aspect ENTROPY = new Aspect("perditio", 4210752, "8", 771);
/*     */ 
/*     */   
/* 168 */   public static final Aspect VOID = new Aspect("vacuos", 8947848, new Aspect[] { AIR, ENTROPY }, 771);
/* 169 */   public static final Aspect LIGHT = new Aspect("lux", 16777152, new Aspect[] { AIR, FIRE });
/* 170 */   public static final Aspect MOTION = new Aspect("motus", 13487348, new Aspect[] { AIR, ORDER });
/* 171 */   public static final Aspect COLD = new Aspect("gelum", 14811135, new Aspect[] { FIRE, ENTROPY });
/* 172 */   public static final Aspect CRYSTAL = new Aspect("vitreus", 8454143, new Aspect[] { EARTH, AIR });
/* 173 */   public static final Aspect METAL = new Aspect("metallum", 11908557, new Aspect[] { EARTH, ORDER });
/* 174 */   public static final Aspect LIFE = new Aspect("victus", 14548997, new Aspect[] { EARTH, WATER });
/* 175 */   public static final Aspect DEATH = new Aspect("mortuus", 6946821, new Aspect[] { WATER, ENTROPY });
/* 176 */   public static final Aspect ENERGY = new Aspect("potentia", 12648447, new Aspect[] { ORDER, FIRE });
/* 177 */   public static final Aspect EXCHANGE = new Aspect("permutatio", 5735255, new Aspect[] { ENTROPY, ORDER });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public static final Aspect MAGIC = new Aspect("praecantatio", 13566207, new Aspect[] { ENERGY, AIR });
/* 187 */   public static final Aspect AURA = new Aspect("auram", 16761087, new Aspect[] { MAGIC, AIR });
/* 188 */   public static final Aspect ALCHEMY = new Aspect("alkimia", 2337949, new Aspect[] { MAGIC, WATER });
/* 189 */   public static final Aspect FLUX = new Aspect("vitium", 8388736, new Aspect[] { ENTROPY, MAGIC });
/*     */   
/* 191 */   public static final Aspect DARKNESS = new Aspect("tenebrae", 2236962, new Aspect[] { VOID, LIGHT });
/* 192 */   public static final Aspect ELDRITCH = new Aspect("alienis", 8409216, new Aspect[] { VOID, DARKNESS });
/* 193 */   public static final Aspect FLIGHT = new Aspect("volatus", 15198167, new Aspect[] { AIR, MOTION });
/* 194 */   public static final Aspect PLANT = new Aspect("herba", 109568, new Aspect[] { LIFE, EARTH });
/*     */   
/* 196 */   public static final Aspect TOOL = new Aspect("instrumentum", 4210926, new Aspect[] { METAL, ENERGY });
/* 197 */   public static final Aspect CRAFT = new Aspect("fabrico", 8428928, new Aspect[] { EXCHANGE, TOOL });
/* 198 */   public static final Aspect MECHANISM = new Aspect("machina", 8421536, new Aspect[] { MOTION, TOOL });
/* 199 */   public static final Aspect TRAP = new Aspect("vinculum", 10125440, new Aspect[] { MOTION, ENTROPY });
/*     */   
/* 201 */   public static final Aspect SOUL = new Aspect("spiritus", 15461371, new Aspect[] { LIFE, DEATH });
/* 202 */   public static final Aspect MIND = new Aspect("cognitio", 16356991, new Aspect[] { FIRE, SOUL });
/* 203 */   public static final Aspect SENSES = new Aspect("sensus", 12648384, new Aspect[] { AIR, SOUL });
/* 204 */   public static final Aspect AVERSION = new Aspect("aversio", 12603472, new Aspect[] { SOUL, ENTROPY });
/* 205 */   public static final Aspect PROTECT = new Aspect("praemunio", 49344, new Aspect[] { SOUL, EARTH });
/* 206 */   public static final Aspect DESIRE = new Aspect("desiderium", 15121988, new Aspect[] { SOUL, VOID });
/*     */   
/* 208 */   public static final Aspect UNDEAD = new Aspect("exanimis", 3817472, new Aspect[] { MOTION, DEATH });
/* 209 */   public static final Aspect BEAST = new Aspect("bestia", 10445833, new Aspect[] { MOTION, LIFE });
/* 210 */   public static final Aspect MAN = new Aspect("humanus", 16766912, new Aspect[] { SOUL, LIFE });
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aspects\Aspect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */