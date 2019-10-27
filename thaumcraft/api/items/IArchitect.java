/*    */ package thaumcraft.api.items;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IArchitect
/*    */ {
/*    */   RayTraceResult getArchitectMOP(ItemStack paramItemStack, World paramWorld, EntityLivingBase paramEntityLivingBase);
/*    */   
/*    */   boolean useBlockHighlight(ItemStack paramItemStack);
/*    */   
/*    */   ArrayList<BlockPos> getArchitectBlocks(ItemStack paramItemStack, World paramWorld, BlockPos paramBlockPos, EnumFacing paramEnumFacing, EntityPlayer paramEntityPlayer);
/*    */   
/*    */   boolean showAxis(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, EnumFacing paramEnumFacing, EnumAxis paramEnumAxis);
/*    */   
/*    */   public enum EnumAxis
/*    */   {
/* 38 */     X,
/* 39 */     Y,
/* 40 */     Z;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\items\IArchitect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */