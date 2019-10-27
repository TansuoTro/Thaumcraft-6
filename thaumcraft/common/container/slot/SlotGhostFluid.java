/*    */ package thaumcraft.common.container.slot;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidUtil;
/*    */ 
/*    */ 
/*    */ public class SlotGhostFluid
/*    */   extends SlotGhost
/*    */ {
/* 12 */   public SlotGhostFluid(IInventory par1iInventory, int par2, int par3, int par4) { super(par1iInventory, par2, par3, par4); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public int func_75219_a() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public boolean func_75214_a(ItemStack stack1) { return (FluidUtil.getFluidHandler(stack1) != null); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public boolean func_82869_a(EntityPlayer par1EntityPlayer) { return false; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\container\slot\SlotGhostFluid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */