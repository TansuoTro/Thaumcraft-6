/*    */ package thaumcraft.common.blocks.world.ore;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.SoundType;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.blocks.BlocksTC;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.common.blocks.BlockTC;
/*    */ 
/*    */ 
/*    */ public class BlockOreTC
/*    */   extends BlockTC
/*    */ {
/*    */   public BlockOreTC(String name) {
/* 24 */     super(Material.field_151576_e, name);
/* 25 */     func_149752_b(5.0F);
/* 26 */     func_149672_a(SoundType.field_185851_d);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Item func_180660_a(IBlockState state, Random rand, int fortune) { return (state.func_177230_c() == BlocksTC.oreQuartz) ? Items.field_151128_bU : ((state.func_177230_c() == BlocksTC.oreAmber) ? ItemsTC.amber : Item.func_150898_a(state.func_177230_c())); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public int func_149745_a(Random random) { return (this == BlocksTC.oreAmber) ? (1 + random.nextInt(2)) : 1; }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
/* 43 */     List<ItemStack> drops = super.getDrops(world, pos, state, fortune);
/* 44 */     if (this == BlocksTC.oreAmber && drops != null) {
/* 45 */       Random rand = (world instanceof World) ? ((World)world).field_73012_v : RANDOM;
/* 46 */       for (int a = 0; a < drops.size(); a++) {
/* 47 */         ItemStack is = (ItemStack)drops.get(a);
/* 48 */         if (is != null && !is.func_190926_b() && is.func_77973_b() == ItemsTC.amber && rand.nextFloat() < 0.066D) {
/* 49 */           drops.set(a, new ItemStack(ItemsTC.curio, 1, 1));
/*    */         }
/*    */       } 
/*    */     } 
/* 53 */     return drops;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
/* 59 */     Random rand = (world instanceof World) ? ((World)world).field_73012_v : new Random();
/* 60 */     if (func_180660_a(state, rand, fortune) != Item.func_150898_a(this)) {
/*    */       
/* 62 */       int j = 0;
/*    */       
/* 64 */       if (this == BlocksTC.oreAmber || this == BlocksTC.oreQuartz)
/*    */       {
/* 66 */         j = MathHelper.func_76136_a(rand, 1, 4);
/*    */       }
/*    */       
/* 69 */       return j;
/*    */     } 
/* 71 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149679_a(int fortune, Random random) {
/* 77 */     if (fortune > 0 && Item.func_150898_a(this) != func_180660_a((IBlockState)func_176194_O().func_177619_a().iterator().next(), random, fortune)) {
/*    */       
/* 79 */       int j = random.nextInt(fortune + 2) - 1;
/*    */       
/* 81 */       if (j < 0)
/*    */       {
/* 83 */         j = 0;
/*    */       }
/*    */       
/* 86 */       return func_149745_a(random) * (j + 1);
/*    */     } 
/*    */ 
/*    */     
/* 90 */     return func_149745_a(random);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\world\ore\BlockOreTC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */