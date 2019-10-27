/*     */ package thaumcraft.api.casters;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EntitySelectors;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ 
/*     */ public class FocusPackage implements IFocusElement {
/*     */   public World world;
/*     */   private EntityLivingBase caster;
/*     */   private UUID casterUUID;
/*     */   
/*  19 */   public String getResearch() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   private float power = 1.0F;
/*  27 */   private int complexity = 0;
/*     */   
/*     */   int index;
/*     */   
/*     */   UUID uid;
/*  32 */   public List<IFocusElement> nodes = Collections.synchronizedList(new ArrayList());
/*     */ 
/*     */   
/*     */   public FocusPackage() {}
/*     */   
/*     */   public FocusPackage(EntityLivingBase caster) {
/*  38 */     this.world = caster.field_70170_p;
/*  39 */     this.caster = caster;
/*  40 */     this.casterUUID = caster.func_110124_au();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  45 */   public String getKey() { return "thaumcraft.PACKAGE"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public IFocusElement.EnumUnitType getType() { return IFocusElement.EnumUnitType.PACKAGE; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public int getComplexity() { return this.complexity; }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public void setComplexity(int complexity) { this.complexity = complexity; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public UUID getUniqueID() { return this.uid; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setUniqueID(UUID id) { this.uid = id; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public int getExecutionIndex() { return this.index; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setExecutionIndex(int idx) { this.index = idx; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void addNode(IFocusElement e) { this.nodes.add(e); }
/*     */ 
/*     */   
/*     */   public UUID getCasterUUID() {
/*  82 */     if (this.caster != null) this.casterUUID = this.caster.func_110124_au(); 
/*  83 */     return this.casterUUID;
/*     */   }
/*     */ 
/*     */   
/*  87 */   public void setCasterUUID(UUID casterUUID) { this.casterUUID = casterUUID; }
/*     */ 
/*     */   
/*     */   public EntityLivingBase getCaster() {
/*     */     try {
/*  92 */       if (this.caster == null) {
/*  93 */         this.caster = this.world.func_152378_a(getCasterUUID());
/*     */       }
/*  95 */       if (this.caster == null) {
/*  96 */         for (EntityLivingBase e : this.world.func_175644_a(EntityLivingBase.class, EntitySelectors.field_94557_a)) {
/*  97 */           if (getCasterUUID().equals(e.func_110124_au())) {
/*  98 */             this.caster = e;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 103 */     } catch (Exception exception) {}
/* 104 */     return this.caster;
/*     */   }
/*     */ 
/*     */   
/* 108 */   public FocusEffect[] getFocusEffects() { return getFocusEffectsPackage(this); }
/*     */ 
/*     */   
/*     */   private FocusEffect[] getFocusEffectsPackage(FocusPackage fp) {
/* 112 */     ArrayList<FocusEffect> out = new ArrayList<FocusEffect>();
/* 113 */     for (IFocusElement el : fp.nodes) {
/* 114 */       if (el instanceof FocusEffect) { out.add((FocusEffect)el); continue; }
/*     */       
/* 116 */       if (el instanceof FocusPackage) {
/* 117 */         for (FocusEffect fep : getFocusEffectsPackage((FocusPackage)el))
/* 118 */           out.add(fep);  continue;
/*     */       } 
/* 120 */       if (el instanceof FocusModSplit)
/* 121 */         for (FocusPackage fsp : ((FocusModSplit)el).getSplitPackages()) {
/* 122 */           for (FocusEffect fep : getFocusEffectsPackage(fsp))
/* 123 */             out.add(fep); 
/*     */         }  
/*     */     } 
/* 126 */     return (FocusEffect[])out.toArray(new FocusEffect[0]);
/*     */   }
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/* 130 */     this.uid = nbt.func_186857_a("uid");
/* 131 */     this.index = nbt.func_74762_e("index");
/* 132 */     int dim = nbt.func_74762_e("dim");
/* 133 */     this.world = DimensionManager.getWorld(dim);
/* 134 */     setCasterUUID(nbt.func_186857_a("casterUUID"));
/* 135 */     this.power = nbt.func_74760_g("power");
/* 136 */     this.complexity = nbt.func_74762_e("complexity");
/*     */     
/* 138 */     NBTTagList nodelist = nbt.func_150295_c("nodes", 10);
/* 139 */     this.nodes.clear();
/* 140 */     for (int x = 0; x < nodelist.func_74745_c(); x++) {
/* 141 */       NBTTagCompound nodenbt = nodelist.func_150305_b(x);
/* 142 */       IFocusElement.EnumUnitType ut = IFocusElement.EnumUnitType.valueOf(nodenbt.func_74779_i("type"));
/* 143 */       if (ut != null) {
/* 144 */         if (ut == IFocusElement.EnumUnitType.PACKAGE) {
/* 145 */           FocusPackage fp = new FocusPackage();
/* 146 */           fp.deserialize(nodenbt.func_74775_l("package"));
/* 147 */           this.nodes.add(fp);
/*     */           break;
/*     */         } 
/* 150 */         IFocusElement fn = FocusEngine.getElement(nodenbt.func_74779_i("key"));
/* 151 */         if (fn != null) {
/* 152 */           if (fn instanceof FocusNode) {
/* 153 */             ((FocusNode)fn).initialize();
/* 154 */             if (((FocusNode)fn).getSettingList() != null) {
/* 155 */               for (String ns : ((FocusNode)fn).getSettingList()) {
/* 156 */                 ((FocusNode)fn).getSetting(ns).setValue(nodenbt.func_74762_e("setting." + ns));
/*     */               }
/*     */             }
/* 159 */             if (fn instanceof FocusModSplit) {
/* 160 */               ((FocusModSplit)fn).deserialize(nodenbt.func_74775_l("packages"));
/*     */             }
/*     */           } 
/* 163 */           addNode(fn);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound serialize() {
/* 172 */     NBTTagCompound nbt = new NBTTagCompound();
/* 173 */     if (this.uid != null) nbt.func_186854_a("uid", this.uid); 
/* 174 */     nbt.func_74768_a("index", this.index);
/* 175 */     if (getCasterUUID() != null) nbt.func_186854_a("casterUUID", getCasterUUID()); 
/* 176 */     if (this.world != null) nbt.func_74768_a("dim", this.world.field_73011_w.getDimension()); 
/* 177 */     nbt.func_74776_a("power", this.power);
/* 178 */     nbt.func_74768_a("complexity", this.complexity);
/*     */ 
/*     */     
/* 181 */     NBTTagList nodelist = new NBTTagList();
/* 182 */     synchronized (this.nodes) {
/* 183 */       for (IFocusElement node : this.nodes) {
/* 184 */         if (node == null || node.getType() == null)
/* 185 */           continue;  NBTTagCompound nodenbt = new NBTTagCompound();
/* 186 */         nodenbt.func_74778_a("type", node.getType().name());
/* 187 */         nodenbt.func_74778_a("key", node.getKey());
/* 188 */         if (node.getType() == IFocusElement.EnumUnitType.PACKAGE) {
/* 189 */           nodenbt.func_74782_a("package", ((FocusPackage)node).serialize());
/* 190 */           nodelist.func_74742_a(nodenbt);
/*     */           break;
/*     */         } 
/* 193 */         if (node instanceof FocusNode && ((FocusNode)node).getSettingList() != null)
/* 194 */           for (String ns : ((FocusNode)node).getSettingList()) {
/* 195 */             nodenbt.func_74768_a("setting." + ns, ((FocusNode)node).getSettingValue(ns));
/*     */           } 
/* 197 */         if (node instanceof FocusModSplit) {
/* 198 */           nodenbt.func_74782_a("packages", ((FocusModSplit)node).serialize());
/*     */         }
/* 200 */         nodelist.func_74742_a(nodenbt);
/*     */       } 
/*     */     } 
/*     */     
/* 204 */     nbt.func_74782_a("nodes", nodelist);
/*     */     
/* 206 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/* 210 */   public float getPower() { return this.power; }
/*     */ 
/*     */ 
/*     */   
/* 214 */   public void multiplyPower(float pow) { this.power *= pow; }
/*     */ 
/*     */   
/*     */   public FocusPackage copy(EntityLivingBase caster) {
/* 218 */     FocusPackage fp = new FocusPackage(caster);
/* 219 */     fp.deserialize(serialize());
/* 220 */     return fp;
/*     */   }
/*     */   
/*     */   public void initialize(EntityLivingBase caster) {
/* 224 */     this.world = caster.func_130014_f_();
/* 225 */     IFocusElement node = (IFocusElement)this.nodes.get(0);
/* 226 */     if (node instanceof FocusMediumRoot && ((FocusMediumRoot)node).supplyTargets() == null) {
/* 227 */       ((FocusMediumRoot)node).setupFromCaster(caster);
/*     */     }
/*     */   }
/*     */   
/*     */   public int getSortingHelper() {
/* 232 */     String s = "";
/* 233 */     for (IFocusElement k : this.nodes) {
/* 234 */       s = s + k.getKey();
/* 235 */       if (k instanceof FocusNode && ((FocusNode)k).getSettingList() != null)
/* 236 */         for (String ns : ((FocusNode)k).getSettingList()) {
/* 237 */           s = s + "" + ((FocusNode)k).getSettingValue(ns);
/*     */         } 
/*     */     } 
/* 240 */     return s.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\casters\FocusPackage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */