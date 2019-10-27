/*    */ package thaumcraft.common.entities.ai.pech;
/*    */ 
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*    */ import thaumcraft.common.entities.monster.EntityPech;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AINearestAttackableTargetPech
/*    */   extends EntityAINearestAttackableTarget
/*    */ {
/* 12 */   public AINearestAttackableTargetPech(EntityCreature p_i45878_1_, Class p_i45878_2_, boolean p_i45878_3_) { super(p_i45878_1_, p_i45878_2_, p_i45878_3_); }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 17 */     if (this.field_75299_d instanceof EntityPech && ((EntityPech)this.field_75299_d).getAnger() == 0)
/* 18 */       return false; 
/* 19 */     return super.func_75250_a();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\entities\ai\pech\AINearestAttackableTargetPech.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */