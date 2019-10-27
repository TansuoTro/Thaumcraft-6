/*    */ package thaumcraft.common.golems.ai;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.pathfinding.Path;
/*    */ import net.minecraft.pathfinding.PathPoint;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import thaumcraft.api.golems.tasks.Task;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*    */ import thaumcraft.common.golems.tasks.TaskHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AIGotoEntity
/*    */   extends AIGoto
/*    */ {
/* 18 */   public AIGotoEntity(EntityThaumcraftGolem g) { super(g, (byte)1); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75246_d() {
/* 23 */     super.func_75246_d();
/* 24 */     if (this.golem.func_70671_ap() != null && this.golem.getTask() != null && this.golem.getTask().getEntity() != null) {
/* 25 */       this.golem.func_70671_ap().func_75651_a(this.golem.getTask().getEntity(), 10.0F, this.golem.func_70646_bf());
/*    */     }
/*    */   }
/*    */   
/*    */   protected void moveTo() {
/* 30 */     if (this.golem.func_70661_as() != null && this.golem.getTask() != null && this.golem.getTask().getEntity() != null) {
/* 31 */       this.golem.func_70661_as().func_75497_a(this.golem.getTask().getEntity(), this.golem.getGolemMoveSpeed());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean findDestination() {
/* 37 */     ArrayList<Task> list = TaskHandler.getEntityTasksSorted(this.golem.field_70170_p.field_73011_w
/* 38 */         .getDimension(), this.golem.func_110124_au(), this.golem);
/* 39 */     for (Task ticket : list) {
/* 40 */       if (areGolemTagsValidForTask(ticket) && ticket.canGolemPerformTask(this.golem) && this.golem
/* 41 */         .func_180485_d(ticket.getEntity().func_180425_c()) && 
/* 42 */         isValidDestination(this.golem.field_70170_p, ticket.getEntity().func_180425_c()) && 
/* 43 */         canEasilyReach(ticket.getEntity())) {
/*    */ 
/*    */         
/* 46 */         this.golem.setTask(ticket);
/* 47 */         this.golem.getTask().setReserved(true);
/* 48 */         this.minDist = 3.5D + ((this.golem.getTask().getEntity()).field_70130_N / 2.0F * (this.golem.getTask().getEntity()).field_70130_N / 2.0F);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 54 */         if (ModConfig.CONFIG_GRAPHICS.showGolemEmotes) this.golem.field_70170_p.func_72960_a(this.golem, (byte)5);
/*    */         
/* 56 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean canEasilyReach(Entity e) {
/* 67 */     if (this.golem.func_70068_e(e) < this.minDist) return true;
/*    */     
/* 69 */     Path pathentity = this.golem.func_70661_as().func_75494_a(e);
/*    */     
/* 71 */     if (pathentity == null)
/*    */     {
/* 73 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 77 */     PathPoint pathpoint = pathentity.func_75870_c();
/*    */     
/* 79 */     if (pathpoint == null)
/*    */     {
/* 81 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 85 */     int i = pathpoint.field_75839_a - MathHelper.func_76128_c(e.field_70165_t);
/* 86 */     int j = pathpoint.field_75838_c - MathHelper.func_76128_c(e.field_70161_v);
/* 87 */     return ((i * i + j * j) < this.minDist);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\AIGotoEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */