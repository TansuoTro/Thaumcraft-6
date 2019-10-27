/*    */ package thaumcraft.common.golems.ai;
/*    */ 
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.EntityLookHelper;
/*    */ import net.minecraft.entity.ai.EntityMoveHelper;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ 
/*    */ 
/*    */ public class FlightMoveHelper
/*    */   extends EntityMoveHelper
/*    */ {
/*    */   private static final String __OBFID = "CL_00002209";
/*    */   
/* 15 */   public FlightMoveHelper(EntityLiving entity) { super(entity); }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75641_c() {
/* 20 */     if (this.field_188491_h == EntityMoveHelper.Action.MOVE_TO && !this.field_75648_a.func_70661_as().func_75500_f()) {
/*    */       
/* 22 */       this.field_188491_h = EntityMoveHelper.Action.WAIT;
/* 23 */       double d0 = this.field_75646_b - this.field_75648_a.field_70165_t;
/* 24 */       double d1 = this.field_75647_c - this.field_75648_a.field_70163_u;
/* 25 */       double d2 = this.field_75644_d - this.field_75648_a.field_70161_v;
/* 26 */       double d3 = d0 * d0 + d1 * d1 + d2 * d2;
/* 27 */       d3 = MathHelper.func_76133_a(d3);
/* 28 */       d1 /= d3;
/* 29 */       float f = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
/* 30 */       this.field_75648_a.field_70177_z = func_75639_a(this.field_75648_a.field_70177_z, f, 30.0F);
/* 31 */       this.field_75648_a.field_70761_aq = this.field_75648_a.field_70177_z;
/* 32 */       float f1 = (float)(this.field_75645_e * this.field_75648_a.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
/* 33 */       this.field_75648_a.func_70659_e(this.field_75648_a.func_70689_ay() + (f1 - this.field_75648_a.func_70689_ay()) * 0.125F);
/* 34 */       double d4 = Math.sin((this.field_75648_a.field_70173_aa + this.field_75648_a.func_145782_y()) * 0.5D) * 0.05D;
/* 35 */       double d5 = Math.cos((this.field_75648_a.field_70177_z * 3.1415927F / 180.0F));
/* 36 */       double d6 = Math.sin((this.field_75648_a.field_70177_z * 3.1415927F / 180.0F));
/* 37 */       this.field_75648_a.field_70159_w += d4 * d5;
/* 38 */       this.field_75648_a.field_70179_y += d4 * d6;
/* 39 */       d4 = Math.sin((this.field_75648_a.field_70173_aa + this.field_75648_a.func_145782_y()) * 0.75D) * 0.05D;
/* 40 */       this.field_75648_a.field_70181_x += d4 * (d6 + d5) * 0.25D;
/* 41 */       this.field_75648_a.field_70181_x += this.field_75648_a.func_70689_ay() * d1 * 0.1D;
/* 42 */       EntityLookHelper entitylookhelper = this.field_75648_a.func_70671_ap();
/* 43 */       double d7 = this.field_75648_a.field_70165_t + d0 / d3 * 2.0D;
/* 44 */       double d8 = this.field_75648_a.func_70047_e() + this.field_75648_a.field_70163_u + d1 / d3 * 1.0D;
/* 45 */       double d9 = this.field_75648_a.field_70161_v + d2 / d3 * 2.0D;
/* 46 */       double d10 = entitylookhelper.func_180423_e();
/* 47 */       double d11 = entitylookhelper.func_180422_f();
/* 48 */       double d12 = entitylookhelper.func_180421_g();
/*    */       
/* 50 */       if (!entitylookhelper.func_180424_b()) {
/*    */         
/* 52 */         d10 = d7;
/* 53 */         d11 = d8;
/* 54 */         d12 = d9;
/*    */       } 
/*    */       
/* 57 */       this.field_75648_a.func_70671_ap().func_75650_a(d10 + (d7 - d10) * 0.125D, d11 + (d8 - d11) * 0.125D, d12 + (d9 - d12) * 0.125D, 10.0F, 40.0F);
/*    */     }
/*    */     else {
/*    */       
/* 61 */       this.field_75648_a.func_70659_e(0.0F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ai\FlightMoveHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */