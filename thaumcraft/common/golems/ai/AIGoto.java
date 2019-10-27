/*     */ package thaumcraft.common.golems.ai;
/*     */ 
/*     */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.seals.ISealEntity;
/*     */ import thaumcraft.api.golems.tasks.Task;
/*     */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*     */ import thaumcraft.common.golems.seals.SealHandler;
/*     */ 
/*     */ public abstract class AIGoto extends EntityAIBase {
/*     */   protected EntityThaumcraftGolem golem;
/*     */   protected int taskCounter;
/*     */   protected byte type;
/*     */   protected int cooldown;
/*     */   protected double minDist;
/*     */   private BlockPos prevRamble;
/*     */   protected BlockPos targetBlock;
/*     */   int pause;
/*     */   
/*  22 */   public AIGoto(EntityThaumcraftGolem g, byte type) { this.taskCounter = -1;
/*  23 */     this.type = 0;
/*     */     
/*  25 */     this.minDist = 4.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.pause = 0; this.golem = g; this.type = type; func_75248_a(5); }
/*     */   public boolean func_75250_a() { if (this.cooldown > 0) { this.cooldown--; return false; }  this.cooldown = 5; if (this.golem.getTask() != null && !this.golem.getTask().isSuspended())
/*     */       return false;  this.targetBlock = null; boolean start = findDestination(); if (start && this.golem.getTask() != null && this.golem.getTask().getSealPos() != null) { ISealEntity se = GolemHelper.getSealEntity(this.golem.field_70170_p.field_73011_w.getDimension(), this.golem.getTask().getSealPos()); if (se != null)
/*     */         se.getSeal().onTaskStarted(this.golem.field_70170_p, this.golem, this.golem.getTask());  }
/* 139 */      return start; } public void func_75251_c() { if (this.golem.getTask() != null) {
/* 140 */       if (!this.golem.getTask().isCompleted() && this.golem.getTask().isReserved() && 
/* 141 */         ModConfig.CONFIG_GRAPHICS.showGolemEmotes) this.golem.field_70170_p.func_72960_a(this.golem, (byte)6);
/*     */       
/* 143 */       if (this.golem.getTask().isCompleted() && !this.golem.getTask().isSuspended()) {
/* 144 */         this.golem.getTask().setSuspended(true);
/*     */       }
/* 146 */       this.golem.getTask().setReserved(false);
/*     */     }  }
/*     */    public void func_75249_e() {
/*     */     moveTo();
/*     */     this.taskCounter = 0;
/*     */   } protected abstract void moveTo(); public boolean func_75253_b() {
/*     */     return (this.taskCounter >= 0 && this.taskCounter <= 1000 && this.golem.getTask() != null && !this.golem.getTask().isSuspended() && isValidDestination(this.golem.field_70170_p, this.golem.getTask().getPos()));
/* 153 */   } protected boolean isValidDestination(World world, BlockPos pos) { return true; }
/*     */ 
/*     */   
/*     */   protected boolean areGolemTagsValidForTask(Task ticket) {
/* 157 */     ISealEntity se = SealHandler.getSealEntity(this.golem.field_70170_p.field_73011_w.getDimension(), ticket.getSealPos());
/* 158 */     if (se != null && se.getSeal() != null) {
/* 159 */       if (se.isLocked() && !this.golem.func_184753_b().equals(se.getOwner())) return false; 
/* 160 */       if (se.getSeal().getRequiredTags() != null && 
/* 161 */         !this.golem.getProperties().getTraits().containsAll(Arrays.asList(se.getSeal().getRequiredTags()))) return false; 
/* 162 */       if (se.getSeal().getForbiddenTags() != null)
/* 163 */         for (EnumGolemTrait tag : se.getSeal().getForbiddenTags()) {
/* 164 */           if (this.golem.getProperties().getTraits().contains(tag)) return false;
/*     */         
/*     */         }  
/*     */     } else {
/* 168 */       return true;
/*     */     } 
/* 170 */     return true;
/*     */   }
/*     */   
/*     */   public void func_75246_d() {
/*     */     if (this.golem.getTask() == null)
/*     */       return; 
/*     */     if (this.pause-- <= 0) {
/*     */       double dist = (this.golem.getTask().getType() == 0) ? this.golem.func_174831_c((this.targetBlock == null) ? this.golem.getTask().getPos() : this.targetBlock) : this.golem.func_70068_e(this.golem.getTask().getEntity());
/*     */       if (dist > this.minDist) {
/*     */         this.golem.getTask().setCompletion(false);
/*     */         this.taskCounter++;
/*     */         if (this.taskCounter % 5 == 0) {
/*     */           if (this.prevRamble != null && this.prevRamble.equals(this.golem.func_180425_c())) {
/*     */             Vec3d vec3 = RandomPositionGenerator.func_75464_a(this.golem, 6, 4, new Vec3d(this.golem.getTask().getPos().func_177958_n(), this.golem.getTask().getPos().func_177956_o(), this.golem.getTask().getPos().func_177952_p()));
/*     */             if (vec3 != null)
/*     */               this.golem.func_70661_as().func_75492_a(vec3.field_72450_a + 0.5D, vec3.field_72448_b + 0.5D, vec3.field_72449_c + 0.5D, this.golem.getGolemMoveSpeed()); 
/*     */           } else {
/*     */             moveTo();
/*     */           } 
/*     */           this.prevRamble = this.golem.func_180425_c();
/*     */         } 
/*     */       } else {
/*     */         TaskHandler.completeTask(this.golem.getTask(), this.golem);
/*     */         if (this.golem.getTask() != null && this.golem.getTask().isCompleted()) {
/*     */           if (this.taskCounter >= 0)
/*     */             this.taskCounter = 0; 
/*     */           this.pause = 0;
/*     */         } else {
/*     */           this.pause = 10;
/*     */           this.taskCounter++;
/*     */         } 
/*     */         this.taskCounter--;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract boolean findDestination();
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\AIGoto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */