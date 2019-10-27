/*     */ package thaumcraft.common.golems.seals;
/*     */ 
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.golems.seals.ISeal;
/*     */ import thaumcraft.api.golems.seals.ISealConfigToggles;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.tasks.TaskHandler;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.misc.PacketSealToClient;
/*     */ 
/*     */ 
/*     */ public class SealEntity
/*     */   implements ISealEntity
/*     */ {
/*     */   SealPos sealPos;
/*     */   ISeal seal;
/*  22 */   byte priority = 0;
/*  23 */   byte color = 0;
/*     */   boolean locked = false;
/*     */   boolean redstone = false;
/*  26 */   String owner = "";
/*     */ 
/*     */   
/*     */   public SealEntity() {}
/*     */ 
/*     */   
/*     */   public SealEntity(World world, SealPos sealPos, ISeal seal) {
/*  33 */     this.sealPos = sealPos;
/*  34 */     this.seal = seal;
/*  35 */     if (seal instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/*  36 */       int x = (sealPos.face.func_82601_c() == 0) ? 3 : 1;
/*  37 */       int y = (sealPos.face.func_96559_d() == 0) ? 3 : 1;
/*  38 */       int z = (sealPos.face.func_82599_e() == 0) ? 3 : 1;
/*  39 */       this.area = new BlockPos(x, y, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickSealEntity(World world) {
/*  45 */     if (this.seal != null) {
/*  46 */       if (isStoppedByRedstone(world)) {
/*  47 */         if (!this.stopped) {
/*  48 */           for (Task t : TaskHandler.getTasks(world.field_73011_w.getDimension()).values()) {
/*  49 */             if (t.getSealPos() != null && t.getSealPos().equals(this.sealPos)) {
/*  50 */               t.setSuspended(true);
/*     */             }
/*     */           } 
/*     */         }
/*  54 */         this.stopped = true;
/*     */         return;
/*     */       } 
/*  57 */       this.stopped = false;
/*  58 */       this.seal.tickSeal(world, this);
/*     */     } 
/*     */   }
/*     */   
/*     */   boolean stopped = false;
/*     */   
/*     */   public boolean isStoppedByRedstone(World world) {
/*  65 */     return (isRedstoneSensitive() && (world
/*  66 */       .func_175640_z((getSealPos()).pos) || world
/*  67 */       .func_175640_z((getSealPos()).pos.func_177972_a((getSealPos()).face))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public ISeal getSeal() { return this.seal; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public SealPos getSealPos() { return this.sealPos; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public byte getPriority() { return this.priority; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public void setPriority(byte priority) { this.priority = priority; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public byte getColor() { return this.color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setColor(byte color) { this.color = color; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getOwner() { return this.owner; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setOwner(String owner) { this.owner = owner; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public boolean isLocked() { return this.locked; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public void setLocked(boolean locked) { this.locked = locked; }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public boolean isRedstoneSensitive() { return this.redstone; }
/*     */ 
/*     */ 
/*     */   
/* 125 */   public void setRedstoneSensitive(boolean redstone) { this.redstone = redstone; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readNBT(NBTTagCompound nbt) {
/* 131 */     BlockPos p = BlockPos.func_177969_a(nbt.func_74763_f("pos"));
/* 132 */     EnumFacing face = EnumFacing.field_82609_l[nbt.func_74771_c("face")];
/* 133 */     this.sealPos = new SealPos(p, face);
/* 134 */     setPriority(nbt.func_74771_c("priority"));
/* 135 */     setColor(nbt.func_74771_c("color"));
/* 136 */     setLocked(nbt.func_74767_n("locked"));
/* 137 */     setRedstoneSensitive(nbt.func_74767_n("redstone"));
/* 138 */     setOwner(nbt.func_74779_i("owner"));
/*     */     try {
/* 140 */       this.seal = (ISeal)SealHandler.getSeal(nbt.func_74779_i("type")).getClass().newInstance();
/* 141 */     } catch (Exception exception) {}
/* 142 */     if (this.seal != null) {
/* 143 */       this.seal.readCustomNBT(nbt);
/* 144 */       if (this.seal instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/* 145 */         this.area = BlockPos.func_177969_a(nbt.func_74763_f("area"));
/*     */       }
/* 147 */       if (this.seal instanceof ISealConfigToggles) {
/* 148 */         for (ISealConfigToggles.SealToggle prop : ((ISealConfigToggles)this.seal).getToggles()) {
/* 149 */           if (nbt.func_74764_b(prop.getKey())) {
/* 150 */             prop.setValue(nbt.func_74767_n(prop.getKey()));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound writeNBT() {
/* 159 */     NBTTagCompound nbt = new NBTTagCompound();
/* 160 */     nbt.func_74772_a("pos", this.sealPos.pos.func_177986_g());
/* 161 */     nbt.func_74774_a("face", (byte)this.sealPos.face.ordinal());
/* 162 */     nbt.func_74778_a("type", this.seal.getKey());
/* 163 */     nbt.func_74774_a("priority", getPriority());
/* 164 */     nbt.func_74774_a("color", getColor());
/* 165 */     nbt.func_74757_a("locked", isLocked());
/* 166 */     nbt.func_74757_a("redstone", isRedstoneSensitive());
/* 167 */     nbt.func_74778_a("owner", getOwner());
/* 168 */     if (this.seal != null) {
/* 169 */       this.seal.writeCustomNBT(nbt);
/* 170 */       if (this.seal instanceof thaumcraft.api.golems.seals.ISealConfigArea) {
/* 171 */         nbt.func_74772_a("area", this.area.func_177986_g());
/*     */       }
/* 173 */       if (this.seal instanceof ISealConfigToggles) {
/* 174 */         for (ISealConfigToggles.SealToggle prop : ((ISealConfigToggles)this.seal).getToggles()) {
/* 175 */           nbt.func_74757_a(prop.getKey(), prop.getValue());
/*     */         }
/*     */       }
/*     */     } 
/* 179 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncToClient(World world) {
/* 184 */     if (!world.field_72995_K) {
/* 185 */       PacketHandler.INSTANCE.sendToDimension(new PacketSealToClient(this), world.field_73011_w.getDimension());
/*     */     }
/*     */   }
/*     */   
/* 189 */   private BlockPos area = new BlockPos(1, 1, 1);
/*     */ 
/*     */ 
/*     */   
/* 193 */   public BlockPos getArea() { return this.area; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public void setArea(BlockPos v) { this.area = v; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\seals\SealEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */