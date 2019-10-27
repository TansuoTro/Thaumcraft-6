/*    */ package thaumcraft.common.blocks.basic;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.WeightedRandom;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.internal.WeightedRandomLoot;
/*    */ import thaumcraft.api.items.ItemGenericEssentiaContainer;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ import thaumcraft.common.config.ModConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStonePorous
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockStonePorous() {
/* 29 */     super(Material.field_151576_e, "stone_porous");
/* 30 */     func_149711_c(1.0F);
/* 31 */     func_149752_b(5.0F);
/* 32 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */   
/* 35 */   static Random r = new Random(System.currentTimeMillis());
/* 36 */   static ArrayList<WeightedRandomLoot> pdrops = null;
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
/* 41 */     List<ItemStack> ret = new ArrayList<ItemStack>();
/* 42 */     int rr = r.nextInt(15) + fortune;
/* 43 */     if (rr > 13) {
/* 44 */       if (pdrops == null || pdrops.size() <= 0) createDrops(); 
/* 45 */       ItemStack s = ((WeightedRandomLoot)WeightedRandom.func_76271_a(r, pdrops)).item.func_77946_l();
/* 46 */       ret.add(s);
/*    */     } else {
/* 48 */       ret.add(new ItemStack(Blocks.field_150351_n));
/*    */     } 
/* 50 */     return ret;
/*    */   }
/*    */   
/*    */   private void createDrops() {
/* 54 */     pdrops = new ArrayList();
/* 55 */     for (Aspect aspect : Aspect.getCompoundAspects()) {
/* 56 */       ItemStack is = new ItemStack(ItemsTC.crystalEssence);
/* 57 */       ((ItemGenericEssentiaContainer)ItemsTC.crystalEssence).setAspects(is, (new AspectList())
/* 58 */           .add(aspect, (aspect == Aspect.FLUX) ? 100 : (aspect.isPrimal() ? 20 : 1)));
/* 59 */       pdrops.add(new WeightedRandomLoot(is, 1));
/*    */     } 
/* 61 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.amber), 20));
/* 62 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 0), 20));
/* 63 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 1), 10));
/* 64 */     pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 6), 10));
/* 65 */     if (ModConfig.foundCopperIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 2), 10)); 
/* 66 */     if (ModConfig.foundTinIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 3), 10)); 
/* 67 */     if (ModConfig.foundSilverIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 4), 8)); 
/* 68 */     if (ModConfig.foundLeadIngot) pdrops.add(new WeightedRandomLoot(new ItemStack(ItemsTC.clusters, 1, 5), 10)); 
/* 69 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151045_i), 2));
/* 70 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151166_bC), 4));
/* 71 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151137_ax), 8));
/* 72 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_179563_cD), 3));
/* 73 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_179562_cC), 3));
/* 74 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151119_aD), 30));
/* 75 */     pdrops.add(new WeightedRandomLoot(new ItemStack(Items.field_151128_bU), 15));
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\basic\BlockStonePorous.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */