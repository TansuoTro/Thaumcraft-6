/*     */ package thaumcraft.common.lib.capabilities;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*     */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.api.research.ResearchCategories;
/*     */ import thaumcraft.api.research.ResearchCategory;
/*     */ import thaumcraft.api.research.ResearchEntry;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncKnowledge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerKnowledge
/*     */ {
/*     */   public static void preInit() {
/*  36 */     CapabilityManager.INSTANCE.register(IPlayerKnowledge.class, new Capability.IStorage<IPlayerKnowledge>()
/*     */         {
/*     */           public NBTTagCompound writeNBT(Capability<IPlayerKnowledge> capability, IPlayerKnowledge instance, EnumFacing side) {
/*  39 */             return (NBTTagCompound)instance.serializeNBT();
/*     */           }
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IPlayerKnowledge> capability, IPlayerKnowledge instance, EnumFacing side, NBTBase nbt) {
/*  44 */             if (nbt instanceof NBTTagCompound) {
/*  45 */               instance.deserializeNBT((NBTTagCompound)nbt);
/*     */             }
/*     */           }
/*  48 */         },  () -> new DefaultImpl(null));
/*     */   }
/*     */   
/*     */   private static class DefaultImpl
/*     */     implements IPlayerKnowledge
/*     */   {
/*  54 */     private final HashSet<String> research = new HashSet();
/*  55 */     private final Map<String, Integer> stages = new HashMap();
/*  56 */     private final Map<String, HashSet<IPlayerKnowledge.EnumResearchFlag>> flags = new HashMap();
/*  57 */     private final Map<String, Integer> knowledge = new HashMap();
/*     */ 
/*     */     
/*     */     public void clear() {
/*  61 */       this.research.clear();
/*  62 */       this.flags.clear();
/*  63 */       this.stages.clear();
/*  64 */       this.knowledge.clear();
/*     */     }
/*     */ 
/*     */     
/*     */     public IPlayerKnowledge.EnumResearchStatus getResearchStatus(@Nonnull String res) {
/*  69 */       if (!isResearchKnown(res)) return IPlayerKnowledge.EnumResearchStatus.UNKNOWN; 
/*  70 */       ResearchEntry entry = ResearchCategories.getResearch(res);
/*  71 */       if (entry == null || entry.getStages() == null || getResearchStage(res) > entry.getStages().length)
/*  72 */         return IPlayerKnowledge.EnumResearchStatus.COMPLETE; 
/*  73 */       return IPlayerKnowledge.EnumResearchStatus.IN_PROGRESS;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isResearchKnown(String res) {
/*  78 */       if (res == null) return false; 
/*  79 */       if (res.equals("")) return true; 
/*  80 */       String[] ss = res.split("@");
/*  81 */       if (ss.length > 1 && getResearchStage(ss[0]) < MathHelper.func_82715_a(ss[1], 0)) return false; 
/*  82 */       return this.research.contains(ss[0]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  87 */     public boolean isResearchComplete(String res) { return (getResearchStatus(res) == IPlayerKnowledge.EnumResearchStatus.COMPLETE); }
/*     */ 
/*     */ 
/*     */     
/*     */     public int getResearchStage(String res) {
/*  92 */       if (res == null || !this.research.contains(res)) return -1; 
/*  93 */       Integer stage = (Integer)this.stages.get(res);
/*  94 */       return (stage == null) ? 0 : stage.intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean setResearchStage(String res, int stage) {
/*  99 */       if (res == null || !this.research.contains(res) || stage <= 0)
/*     */       {
/* 101 */         return false;
/*     */       }
/* 103 */       this.stages.put(res, Integer.valueOf(stage));
/* 104 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean addResearch(@Nonnull String res) {
/* 109 */       if (!isResearchKnown(res)) {
/* 110 */         this.research.add(res);
/* 111 */         return true;
/*     */       } 
/* 113 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean removeResearch(@Nonnull String res) {
/* 118 */       if (isResearchKnown(res)) {
/* 119 */         this.research.remove(res);
/* 120 */         return true;
/*     */       } 
/* 122 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nonnull
/* 127 */     public Set<String> getResearchList() { return Collections.unmodifiableSet(this.research); }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean setResearchFlag(@Nonnull String res, @Nonnull IPlayerKnowledge.EnumResearchFlag flag) {
/* 132 */       HashSet<IPlayerKnowledge.EnumResearchFlag> list = (HashSet)this.flags.get(res);
/* 133 */       if (list == null) {
/* 134 */         list = new HashSet<IPlayerKnowledge.EnumResearchFlag>();
/* 135 */         this.flags.put(res, list);
/*     */       } 
/* 137 */       if (list.contains(flag)) return false; 
/* 138 */       list.add(flag);
/* 139 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean clearResearchFlag(@Nonnull String res, @Nonnull IPlayerKnowledge.EnumResearchFlag flag) {
/* 144 */       HashSet<IPlayerKnowledge.EnumResearchFlag> list = (HashSet)this.flags.get(res);
/* 145 */       if (list != null) {
/* 146 */         boolean b = list.remove(flag);
/* 147 */         if (list.isEmpty())
/* 148 */           this.flags.remove(this.research); 
/* 149 */         return b;
/*     */       } 
/* 151 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasResearchFlag(@Nonnull String res, @Nonnull IPlayerKnowledge.EnumResearchFlag flag) {
/* 156 */       if (this.flags.get(res) != null) {
/* 157 */         return ((HashSet)this.flags.get(res)).contains(flag);
/*     */       }
/* 159 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 163 */     private String getKey(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category) { return type.getAbbreviation() + "_" + ((category == null) ? "" : category.key); }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean addKnowledge(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category, int amount) {
/* 168 */       String key = getKey(type, category);
/* 169 */       int c = getKnowledgeRaw(type, category);
/* 170 */       if (c + amount < 0) return false; 
/* 171 */       c += amount;
/* 172 */       this.knowledge.put(key, Integer.valueOf(c));
/* 173 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getKnowledge(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category) {
/* 178 */       String key = getKey(type, category);
/* 179 */       int c = !this.knowledge.containsKey(key) ? 0 : ((Integer)this.knowledge.get(key)).intValue();
/* 180 */       return (int)Math.floor(c / type.getProgression());
/*     */     }
/*     */ 
/*     */     
/*     */     public int getKnowledgeRaw(IPlayerKnowledge.EnumKnowledgeType type, ResearchCategory category) {
/* 185 */       String key = getKey(type, category);
/* 186 */       return !this.knowledge.containsKey(key) ? 0 : ((Integer)this.knowledge.get(key)).intValue();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 192 */     public void sync(@Nonnull EntityPlayerMP player) { PacketHandler.INSTANCE.sendTo(new PacketSyncKnowledge(player), player); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public NBTTagCompound serializeNBT() {
/* 198 */       NBTTagCompound rootTag = new NBTTagCompound();
/* 199 */       NBTTagList researchList = new NBTTagList();
/* 200 */       for (String resKey : this.research) {
/*     */         
/* 202 */         NBTTagCompound tag = new NBTTagCompound();
/* 203 */         tag.func_74778_a("key", resKey);
/* 204 */         if (this.stages.containsKey(resKey)) {
/* 205 */           tag.func_74768_a("stage", ((Integer)this.stages.get(resKey)).intValue());
/*     */         }
/* 207 */         if (this.flags.containsKey(resKey)) {
/* 208 */           HashSet<IPlayerKnowledge.EnumResearchFlag> list = (HashSet)this.flags.get(resKey);
/* 209 */           if (list != null) {
/* 210 */             String fs = "";
/* 211 */             for (IPlayerKnowledge.EnumResearchFlag flag : list) {
/* 212 */               if (fs.length() > 0) fs = fs + ","; 
/* 213 */               fs = fs + flag.name();
/*     */             } 
/* 215 */             tag.func_74778_a("flags", fs);
/*     */           } 
/*     */         } 
/*     */         
/* 219 */         researchList.func_74742_a(tag);
/*     */       } 
/* 221 */       rootTag.func_74782_a("research", researchList);
/*     */       
/* 223 */       NBTTagList knowledgeList = new NBTTagList();
/* 224 */       for (String key : this.knowledge.keySet()) {
/*     */         
/* 226 */         Integer c = (Integer)this.knowledge.get(key);
/* 227 */         if (c != null && c.intValue() > 0 && key != null && !key.isEmpty()) {
/* 228 */           NBTTagCompound tag = new NBTTagCompound();
/* 229 */           tag.func_74778_a("key", key);
/* 230 */           tag.func_74768_a("amount", c.intValue());
/* 231 */           knowledgeList.func_74742_a(tag);
/*     */         } 
/*     */       } 
/* 234 */       rootTag.func_74782_a("knowledge", knowledgeList);
/*     */       
/* 236 */       return rootTag;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void deserializeNBT(NBTTagCompound rootTag) {
/* 242 */       if (rootTag == null)
/*     */         return; 
/* 244 */       clear();
/*     */       
/* 246 */       NBTTagList researchList = rootTag.func_150295_c("research", 10);
/* 247 */       for (int i = 0; i < researchList.func_74745_c(); i++) {
/*     */         
/* 249 */         NBTTagCompound tag = researchList.func_150305_b(i);
/* 250 */         String know = tag.func_74779_i("key");
/* 251 */         if (know != null && !isResearchKnown(know)) {
/*     */           
/* 253 */           this.research.add(know);
/* 254 */           int stage = tag.func_74762_e("stage");
/* 255 */           if (stage > 0) {
/* 256 */             this.stages.put(know, Integer.valueOf(stage));
/*     */           }
/* 258 */           String fs = tag.func_74779_i("flags");
/* 259 */           if (fs.length() > 0) {
/* 260 */             String[] ss = fs.split(",");
/* 261 */             for (String s : ss) {
/* 262 */               IPlayerKnowledge.EnumResearchFlag flag = null; 
/* 263 */               try { flag = IPlayerKnowledge.EnumResearchFlag.valueOf(s); } catch (Exception exception) {}
/* 264 */               if (flag != null) setResearchFlag(know, flag);
/*     */             
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 271 */       NBTTagList knowledgeList = rootTag.func_150295_c("knowledge", 10);
/* 272 */       for (int i = 0; i < knowledgeList.func_74745_c(); i++) {
/*     */         
/* 274 */         NBTTagCompound tag = knowledgeList.func_150305_b(i);
/* 275 */         String key = tag.func_74779_i("key");
/* 276 */         int amount = tag.func_74762_e("amount");
/* 277 */         this.knowledge.put(key, Integer.valueOf(amount));
/*     */       } 
/*     */       
/* 280 */       addAutoUnlockResearch();
/*     */     }
/*     */ 
/*     */     
/*     */     private void addAutoUnlockResearch() {
/* 285 */       for (ResearchCategory cat : ResearchCategories.researchCategories.values()) {
/* 286 */         for (ResearchEntry ri : cat.research.values()) {
/* 287 */           if (ri.hasMeta(ResearchEntry.EnumResearchMeta.AUTOUNLOCK)) {
/* 288 */             addResearch(ri.getKey());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private DefaultImpl() {}
/*     */   }
/*     */   
/*     */   public static class Provider
/*     */     extends Object
/*     */     implements ICapabilitySerializable<NBTTagCompound>
/*     */   {
/* 301 */     public static final ResourceLocation NAME = new ResourceLocation("thaumcraft", "knowledge");
/*     */     
/* 303 */     private final PlayerKnowledge.DefaultImpl knowledge = new PlayerKnowledge.DefaultImpl(null);
/*     */ 
/*     */ 
/*     */     
/* 307 */     public boolean hasCapability(Capability<?> capability, EnumFacing facing) { return (capability == ThaumcraftCapabilities.KNOWLEDGE); }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
/* 312 */       if (capability == ThaumcraftCapabilities.KNOWLEDGE)
/*     */       {
/* 314 */         return (T)ThaumcraftCapabilities.KNOWLEDGE.cast(this.knowledge);
/*     */       }
/* 316 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 322 */     public NBTTagCompound serializeNBT() { return this.knowledge.serializeNBT(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     public void deserializeNBT(NBTTagCompound nbt) { this.knowledge.deserializeNBT(nbt); }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\capabilities\PlayerKnowledge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */