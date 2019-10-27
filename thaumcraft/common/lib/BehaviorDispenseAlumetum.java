/*    */ package thaumcraft.common.lib;
/*    */ 
/*    */ import net.minecraft.dispenser.BehaviorProjectileDispense;
/*    */ import net.minecraft.dispenser.IPosition;
/*    */ import net.minecraft.entity.IProjectile;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.common.entities.projectile.EntityAlumentum;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorDispenseAlumetum
/*    */   extends BehaviorProjectileDispense
/*    */ {
/* 19 */   protected IProjectile func_82499_a(World worldIn, IPosition position, ItemStack stackIn) { return new EntityAlumentum(worldIn, position.func_82615_a(), position.func_82617_b(), position.func_82616_c()); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\BehaviorDispenseAlumetum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */