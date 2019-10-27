/*    */ package thaumcraft.common.items.tools;
/*    */ 
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.SoundEvents;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraft.util.EnumActionResult;
/*    */ import net.minecraft.util.EnumFacing;
/*    */ import net.minecraft.util.EnumHand;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import thaumcraft.api.aspects.Aspect;
/*    */ import thaumcraft.api.aspects.IAspectContainer;
/*    */ import thaumcraft.api.aspects.IEssentiaTransport;
/*    */ import thaumcraft.codechicken.lib.raytracer.RayTracer;
/*    */ import thaumcraft.common.config.ConfigItems;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ import thaumcraft.common.tiles.devices.TileCondenser;
/*    */ 
/*    */ 
/*    */ public class ItemResonator
/*    */   extends ItemTCBase
/*    */ {
/*    */   public ItemResonator() {
/* 30 */     super("resonator", new String[0]);
/* 31 */     func_77625_d(1);
/* 32 */     func_77625_d(1);
/* 33 */     func_77637_a(ConfigItems.TABTC);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public boolean func_77636_d(ItemStack stack1) { return stack1.func_77942_o(); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/* 54 */     TileEntity tile = world.func_175625_s(pos);
/* 55 */     if (tile != null && tile instanceof IEssentiaTransport) {
/* 56 */       if (world.field_72995_K) {
/* 57 */         player.func_184609_a(hand);
/* 58 */         return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*    */       } 
/* 60 */       IEssentiaTransport et = (IEssentiaTransport)tile;
/*    */       
/* 62 */       RayTraceResult hit = RayTracer.retraceBlock(world, player, pos);
/* 63 */       if (hit != null && hit.subHit >= 0 && hit.subHit < 6)
/*    */       {
/* 65 */         side = EnumFacing.field_82609_l[hit.subHit];
/*    */       }
/*    */       
/* 68 */       if (!(tile instanceof thaumcraft.common.tiles.essentia.TileTubeBuffer) && et.getEssentiaType(side) != null) {
/* 69 */         player.func_145747_a(new TextComponentTranslation("tc.resonator1", new Object[] { "" + et
/* 70 */                 .getEssentiaAmount(side), et.getEssentiaType(side).getName() }));
/* 71 */       } else if (tile instanceof thaumcraft.common.tiles.essentia.TileTubeBuffer && ((IAspectContainer)tile).getAspects().size() > 0) {
/* 72 */         for (Aspect aspect : ((IAspectContainer)tile).getAspects().getAspectsSortedByName()) {
/* 73 */           player.func_145747_a(new TextComponentTranslation("tc.resonator1", new Object[] { "" + ((IAspectContainer)tile)
/* 74 */                   .getAspects().getAmount(aspect), aspect
/* 75 */                   .getName() }));
/*    */         } 
/*    */       } 
/* 78 */       String s = I18n.func_74838_a("tc.resonator3");
/* 79 */       if (et.getSuctionType(side) != null)
/* 80 */         s = et.getSuctionType(side).getName(); 
/* 81 */       player.func_145747_a(new TextComponentTranslation("tc.resonator2", new Object[] { "" + et
/* 82 */               .getSuctionAmount(side), s }));
/*    */       
/* 84 */       world.func_184148_a(null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundEvents.field_187767_eL, SoundCategory.BLOCKS, 0.5F, 1.9F + world.field_73012_v.nextFloat() * 0.1F);
/*    */ 
/*    */       
/* 87 */       if (tile != null && tile instanceof TileCondenser) {
/* 88 */         TileCondenser tc = (TileCondenser)tile;
/* 89 */         player.func_145747_a(new TextComponentTranslation("tc.condenser1", new Object[] { "" + tc.cost }));
/* 90 */         int s = tc.interval / 20;
/* 91 */         player.func_145747_a(new TextComponentTranslation("tc.condenser2", new Object[] { "" + tc.interval, "" + s }));
/*    */       } 
/*    */       
/* 94 */       return EnumActionResult.SUCCESS;
/*    */     } 
/*    */     
/* 97 */     return EnumActionResult.FAIL;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemResonator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */