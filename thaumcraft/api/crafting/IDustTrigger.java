/*    */ package thaumcraft.api.crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IDustTrigger
/*    */ {
/*    */   Placement getValidFace(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, EnumFacing paramEnumFacing);
/*    */   
/*    */   void execute(World paramWorld, EntityPlayer paramEntityPlayer, BlockPos paramBlockPos, Placement paramPlacement, EnumFacing paramEnumFacing);
/*    */   
/*    */   public static class Placement
/*    */   {
/*    */     public int xOffset;
/*    */     public int yOffset;
/*    */     public int zOffset;
/*    */     public EnumFacing facing;
/*    */     
/*    */     public Placement(int xOffset, int yOffset, int zOffset, EnumFacing facing) {
/* 29 */       this.xOffset = xOffset;
/* 30 */       this.yOffset = yOffset;
/* 31 */       this.zOffset = zOffset;
/* 32 */       this.facing = facing;
/*    */     }
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 58 */   default List<BlockPos> sparkle(World world, EntityPlayer player, BlockPos pos, Placement placement) { return Arrays.asList(new BlockPos[] { pos }); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public static final ArrayList<IDustTrigger> triggers = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   static void registerDustTrigger(IDustTrigger trigger) { triggers.add(trigger); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\crafting\IDustTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */