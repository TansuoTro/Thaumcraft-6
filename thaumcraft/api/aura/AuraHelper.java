/*    */ package thaumcraft.api.aura;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.ThaumcraftApi;
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
/*    */ public class AuraHelper
/*    */ {
/* 20 */   public static float drainVis(World world, BlockPos pos, float amount, boolean simulate) { return ThaumcraftApi.internalMethods.drainVis(world, pos, amount, simulate); }
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
/* 33 */   public static float drainFlux(World world, BlockPos pos, float amount, boolean simulate) { return ThaumcraftApi.internalMethods.drainFlux(world, pos, amount, simulate); }
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
/* 44 */   public static void addVis(World world, BlockPos pos, float amount) { ThaumcraftApi.internalMethods.addVis(world, pos, amount); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public static float getVis(World world, BlockPos pos) { return ThaumcraftApi.internalMethods.getVis(world, pos); }
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
/* 65 */   public static void polluteAura(World world, BlockPos pos, float amount, boolean showEffect) { ThaumcraftApi.internalMethods.addFlux(world, pos, amount, showEffect); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 75 */   public static float getFlux(World world, BlockPos pos) { return ThaumcraftApi.internalMethods.getFlux(world, pos); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 85 */   public static int getAuraBase(World world, BlockPos pos) { return ThaumcraftApi.internalMethods.getAuraBase(world, pos); }
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
/* 97 */   public static boolean shouldPreserveAura(World world, EntityPlayer player, BlockPos pos) { return ThaumcraftApi.internalMethods.shouldPreserveAura(world, player, pos); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\aura\AuraHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */