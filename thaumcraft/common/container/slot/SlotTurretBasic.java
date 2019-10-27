/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import thaumcraft.common.entities.construct.EntityTurretCrossbow;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotTurretBasic
/*    */   extends SlotMobEquipment
/*    */ {
/* 12 */   public SlotTurretBasic(EntityTurretCrossbow turret, int par3, int par4, int par5) { super(turret, par3, par4, par5); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 19 */   public boolean func_75214_a(ItemStack stack) { return (stack != null && !stack.func_190926_b() && stack.func_77973_b() != null && stack.func_77973_b() instanceof net.minecraft.item.ItemArrow); }
/*    */   
/*    */   public void func_75218_e() {}
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotTurretBasic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */