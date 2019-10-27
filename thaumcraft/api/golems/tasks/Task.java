/*     */ package thaumcraft.api.golems.tasks;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import thaumcraft.api.golems.GolemHelper;
/*     */ import thaumcraft.api.golems.IGolemAPI;
/*     */ import thaumcraft.api.golems.ProvisionRequest;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.seals.SealPos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Task
/*     */ {
/*     */   private UUID golemUUID;
/*     */   private int id;
/*     */   private byte type;
/*     */   private SealPos sealPos;
/*     */   private BlockPos pos;
/*     */   private Entity entity;
/*     */   private boolean reserved;
/*     */   private boolean suspended;
/*     */   private boolean completed;
/*     */   private int data;
/*     */   private ProvisionRequest linkedProvision;
/*     */   private short lifespan;
/*  30 */   private byte priority = 0;
/*     */   
/*     */   private Task() {}
/*     */   
/*     */   public Task(SealPos sealPos, BlockPos pos) {
/*  35 */     this.sealPos = sealPos;
/*  36 */     this.pos = pos;
/*  37 */     if (sealPos == null) {
/*  38 */       this.id = (System.currentTimeMillis() + "/BNPOS/" + pos.toString()).hashCode();
/*     */     } else {
/*  40 */       this.id = (System.currentTimeMillis() + "/B/" + sealPos.face.toString() + "/" + sealPos.pos.toString() + "/" + pos.toString()).hashCode();
/*  41 */     }  this.type = 0;
/*  42 */     this.lifespan = 300;
/*     */   }
/*     */   
/*     */   public Task(SealPos sealPos, Entity entity) {
/*  46 */     this.sealPos = sealPos;
/*  47 */     this.entity = entity;
/*  48 */     if (sealPos == null) {
/*  49 */       this.id = (System.currentTimeMillis() + "/ENPOS/" + entity.func_145782_y()).hashCode();
/*     */     } else {
/*  51 */       this.id = (System.currentTimeMillis() + "/E/" + sealPos.face.toString() + "/" + sealPos.pos.toString() + "/" + entity.func_145782_y()).hashCode();
/*  52 */     }  this.type = 1;
/*  53 */     this.lifespan = 300;
/*     */   }
/*     */ 
/*     */   
/*  57 */   public byte getPriority() { return this.priority; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public void setPriority(byte priority) { this.priority = priority; }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public boolean isCompleted() { return this.completed; }
/*     */ 
/*     */   
/*     */   public void setCompletion(boolean fulfilled) {
/*  69 */     this.completed = fulfilled;
/*  70 */     this.lifespan = (short)(this.lifespan + 1);
/*     */   }
/*     */ 
/*     */   
/*  74 */   public UUID getGolemUUID() { return this.golemUUID; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setGolemUUID(UUID golemUUID) { this.golemUUID = golemUUID; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public BlockPos getPos() { return (this.type == 1) ? this.entity.func_180425_c() : this.pos; }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public byte getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public Entity getEntity() { return this.entity; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public int getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public boolean isReserved() { return this.reserved; }
/*     */ 
/*     */   
/*     */   public void setReserved(boolean res) {
/* 102 */     this.reserved = res;
/* 103 */     this.lifespan = (short)(this.lifespan + 120);
/*     */   }
/*     */ 
/*     */   
/* 107 */   public boolean isSuspended() { return this.suspended; }
/*     */ 
/*     */   
/*     */   public void setSuspended(boolean suspended) {
/* 111 */     setLinkedProvision(null);
/* 112 */     this.suspended = suspended;
/*     */   }
/*     */ 
/*     */   
/* 116 */   public SealPos getSealPos() { return this.sealPos; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 121 */     if (!(o instanceof Task))
/*     */     {
/* 123 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 127 */     Task t = (Task)o;
/* 128 */     return (t.id == this.id);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public long getLifespan() { return this.lifespan; }
/*     */ 
/*     */ 
/*     */   
/* 137 */   public void setLifespan(short ls) { this.lifespan = ls; }
/*     */ 
/*     */   
/*     */   public boolean canGolemPerformTask(IGolemAPI golem) {
/* 141 */     ISealEntity se = GolemHelper.getSealEntity((golem.getGolemWorld()).field_73011_w.getDimension(), this.sealPos);
/* 142 */     if (se != null) {
/* 143 */       if (golem.getGolemColor() > 0 && se.getColor() > 0 && golem.getGolemColor() != se.getColor()) return false; 
/* 144 */       return se.getSeal().canGolemPerformTask(golem, this);
/*     */     } 
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public int getData() { return this.data; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setData(int data) { this.data = data; }
/*     */ 
/*     */ 
/*     */   
/* 159 */   public ProvisionRequest getLinkedProvision() { return this.linkedProvision; }
/*     */ 
/*     */ 
/*     */   
/* 163 */   public void setLinkedProvision(ProvisionRequest linkedProvision) { this.linkedProvision = linkedProvision; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\golems\tasks\Task.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */