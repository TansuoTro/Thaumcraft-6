/*    */ package thaumcraft.common.blocks;
/*    */ 
/*    */ import net.minecraft.block.ITileEntityProvider;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.api.aura.AuraHelper;
/*    */ import thaumcraft.common.lib.utils.InventoryUtils;
/*    */ 
/*    */ 
/*    */ public class BlockTCTile
/*    */   extends BlockTC
/*    */   implements ITileEntityProvider
/*    */ {
/*    */   protected final Class tileClass;
/*    */   
/*    */   public BlockTCTile(Material mat, Class tc, String name) {
/* 25 */     super(mat, name);
/* 26 */     func_149711_c(2.0F);
/* 27 */     func_149752_b(20.0F);
/* 28 */     this.tileClass = tc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   public TileEntity func_149915_a(World worldIn, int meta) {
/* 39 */     if (this.tileClass == null) return null; 
/*    */     try {
/* 41 */       return (TileEntity)this.tileClass.newInstance();
/* 42 */     } catch (InstantiationException e) {
/* 43 */       Thaumcraft.log.catching(e);
/* 44 */     } catch (IllegalAccessException e) {
/* 45 */       Thaumcraft.log.catching(e);
/*    */     } 
/* 47 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public boolean hasTileEntity(IBlockState state) { return true; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static boolean keepInventory = false;
/*    */   
/*    */   protected static boolean spillEssentia = true;
/*    */ 
/*    */   
/*    */   public void func_180663_b(World worldIn, BlockPos pos, IBlockState state) {
/* 62 */     InventoryUtils.dropItems(worldIn, pos);
/*    */     
/* 64 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/* 65 */     if (tileentity != null && tileentity instanceof IEssentiaTransport && spillEssentia && !worldIn.field_72995_K) {
/*    */       
/* 67 */       int ess = ((IEssentiaTransport)tileentity).getEssentiaAmount(EnumFacing.UP);
/* 68 */       if (ess > 0) {
/* 69 */         AuraHelper.polluteAura(worldIn, pos, ess, true);
/*    */       }
/*    */     } 
/*    */     
/* 73 */     super.func_180663_b(worldIn, pos, state);
/* 74 */     worldIn.func_175713_t(pos);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_189539_a(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
/* 80 */     super.func_189539_a(state, worldIn, pos, id, param);
/* 81 */     TileEntity tileentity = worldIn.func_175625_s(pos);
/* 82 */     return (tileentity == null) ? false : tileentity.func_145842_c(id, param);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\BlockTCTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */