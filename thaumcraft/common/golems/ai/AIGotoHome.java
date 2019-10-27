/*    */ package thaumcraft.common.golems.ai;
/*    */ 
/*    */ import net.minecraft.entity.ai.EntityAIBase;
/*    */ import net.minecraft.entity.ai.RandomPositionGenerator;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import thaumcraft.common.golems.EntityThaumcraftGolem;
/*    */ 
/*    */ public class AIGotoHome
/*    */   extends EntityAIBase
/*    */ {
/*    */   protected EntityThaumcraftGolem golem;
/*    */   private double movePosX;
/*    */   
/*    */   public AIGotoHome(EntityThaumcraftGolem g) {
/* 15 */     this.idleCounter = 10;
/*    */ 
/*    */ 
/*    */     
/* 19 */     this.golem = g;
/* 20 */     func_75248_a(5);
/*    */   }
/*    */   private double movePosY; private double movePosZ;
/*    */   protected int idleCounter;
/*    */   
/*    */   public boolean func_75250_a() {
/* 26 */     if (this.idleCounter > 0) {
/*    */       
/* 28 */       this.idleCounter--;
/* 29 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 33 */     this.idleCounter = 50;
/* 34 */     double dd = this.golem.func_174831_c(this.golem.func_180486_cf());
/* 35 */     if (dd < 5.0D) return false; 
/* 36 */     if (dd > 1024.0D) {
/* 37 */       Vec3d vec3 = RandomPositionGenerator.func_75464_a(this.golem, 16, 7, new Vec3d(this.golem
/* 38 */             .func_180486_cf().func_177958_n(), this.golem.func_180486_cf().func_177956_o(), this.golem.func_180486_cf().func_177952_p()));
/*    */       
/* 40 */       if (vec3 == null)
/*    */       {
/* 42 */         return false;
/*    */       }
/*    */ 
/*    */       
/* 46 */       this.movePosX = vec3.field_72450_a;
/* 47 */       this.movePosY = vec3.field_72448_b;
/* 48 */       this.movePosZ = vec3.field_72449_c;
/* 49 */       return true;
/*    */     } 
/*    */     
/* 52 */     this.movePosX = this.golem.func_180486_cf().func_177958_n();
/* 53 */     this.movePosY = this.golem.func_180486_cf().func_177956_o();
/* 54 */     this.movePosZ = this.golem.func_180486_cf().func_177952_p();
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public void func_75249_e() { this.golem.func_70661_as().func_75492_a(this.movePosX, this.movePosY, this.movePosZ, this.golem.getGolemMoveSpeed()); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public boolean func_75253_b() { return (this.golem.getTask() == null && !this.golem.func_70661_as().func_75500_f() && this.golem.func_174831_c(this.golem.func_180486_cf()) > 3.0D); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75251_c() {
/* 75 */     this.idleCounter = 50;
/* 76 */     this.golem.func_70661_as().func_75499_g();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\AIGotoHome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */