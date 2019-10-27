/*    */ package thaumcraft.common.items.resources;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.crafting.IDustTrigger;
/*    */ import thaumcraft.client.fx.FXDispatcher;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ import thaumcraft.common.lib.SoundsTC;
/*    */ import thaumcraft.common.lib.utils.EntityUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMagicDust
/*    */   extends ItemTCBase
/*    */ {
/* 27 */   public ItemMagicDust() { super("salis_mundus", new String[0]); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 40 */     if (!player.func_175151_a(pos, side, player.func_184586_b(hand)))
/*    */     {
/* 42 */       return EnumActionResult.FAIL;
/*    */     }
/*    */     
/* 45 */     if (player.func_70093_af()) return EnumActionResult.PASS;
/*    */     
/* 47 */     player.func_184609_a(hand);
/*    */     
/* 49 */     for (IDustTrigger trigger : IDustTrigger.triggers) {
/* 50 */       IDustTrigger.Placement place = trigger.getValidFace(world, player, pos, side);
/* 51 */       if (place != null) {
/* 52 */         if (!player.field_71075_bZ.field_75098_d) {
/* 53 */           player.func_184586_b(hand).func_190918_g(1);
/*    */         }
/* 55 */         trigger.execute(world, player, pos, place, side);
/*    */         
/* 57 */         if (world.field_72995_K) {
/* 58 */           doSparkles(player, world, pos, hitX, hitY, hitZ, hand, trigger, place);
/*    */           break;
/*    */         } 
/* 61 */         return EnumActionResult.SUCCESS;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 66 */     return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*    */   }
/*    */ 
/*    */   
/*    */   private void doSparkles(EntityPlayer player, World world, BlockPos pos, float hitX, float hitY, float hitZ, EnumHand hand, IDustTrigger trigger, IDustTrigger.Placement place) {
/* 71 */     Vec3d v1 = EntityUtils.posToHand(player, hand);
/* 72 */     Vec3d v2 = new Vec3d(pos);
/* 73 */     v2 = v2.func_72441_c(0.5D, 0.5D, 0.5D);
/* 74 */     v2 = v2.func_178788_d(v1);
/* 75 */     int cnt = 50;
/* 76 */     for (int a = 0; a < cnt; a++) {
/* 77 */       boolean floaty = (a < cnt / 3);
/* 78 */       float r = MathHelper.func_76136_a(world.field_73012_v, 255, 255) / 255.0F;
/* 79 */       float g = MathHelper.func_76136_a(world.field_73012_v, 189, 255) / 255.0F;
/* 80 */       float b = MathHelper.func_76136_a(world.field_73012_v, 64, 255) / 255.0F;
/* 81 */       FXDispatcher.INSTANCE.drawSimpleSparkle(world.field_73012_v, v1.field_72450_a, v1.field_72448_b, v1.field_72449_c, v2.field_72450_a / 6.0D + world.field_73012_v
/*    */           
/* 83 */           .nextGaussian() * 0.05D, v2.field_72448_b / 6.0D + world.field_73012_v
/* 84 */           .nextGaussian() * 0.05D + (floaty ? 0.05D : 0.15D), v2.field_72449_c / 6.0D + world.field_73012_v
/* 85 */           .nextGaussian() * 0.05D, 0.5F, r, g, b, world.field_73012_v
/* 86 */           .nextInt(5), floaty ? (0.3F + world.field_73012_v
/* 87 */           .nextFloat() * 0.5F) : 0.85F, floaty ? 0.2F : 0.5F, 16);
/*    */     } 
/* 89 */     world.func_184134_a(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundsTC.dust, SoundCategory.PLAYERS, 0.33F, 1.0F + 
/* 90 */         (float)world.field_73012_v.nextGaussian() * 0.05F, false);
/*    */ 
/*    */     
/* 93 */     List<BlockPos> sparkles = trigger.sparkle(world, player, pos, place);
/* 94 */     if (sparkles != null) {
/* 95 */       Vec3d v = (new Vec3d(pos)).func_72441_c(hitX, hitY, hitZ);
/* 96 */       for (BlockPos p : sparkles)
/* 97 */         FXDispatcher.INSTANCE.drawBlockSparkles(p, v); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\resources\ItemMagicDust.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */