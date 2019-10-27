/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.Thaumcraft;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ 
/*     */ 
/*     */ public class ItemHandMirror
/*     */   extends ItemTCBase
/*     */ {
/*     */   public ItemHandMirror() {
/*  37 */     super("hand_mirror", new String[0]);
/*  38 */     func_77625_d(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public boolean func_77651_p() { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   public boolean func_77636_d(ItemStack stack1) { return stack1.func_77942_o(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float par8, float par9, float par10, EnumHand hand) {
/*  61 */     Block bi = world.func_180495_p(pos).func_177230_c();
/*  62 */     if (bi == BlocksTC.mirror) {
/*  63 */       if (world.field_72995_K) {
/*  64 */         player.func_184609_a(hand);
/*  65 */         return super.onItemUseFirst(player, world, pos, side, par8, par9, par10, hand);
/*     */       } 
/*  67 */       TileEntity tm = world.func_175625_s(pos);
/*  68 */       if (tm != null && tm instanceof TileMirror) {
/*  69 */         player.func_184586_b(hand).func_77983_a("linkX", new NBTTagInt(pos.func_177958_n()));
/*  70 */         player.func_184586_b(hand).func_77983_a("linkY", new NBTTagInt(pos.func_177956_o()));
/*  71 */         player.func_184586_b(hand).func_77983_a("linkZ", new NBTTagInt(pos.func_177952_p()));
/*  72 */         player.func_184586_b(hand).func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.getDimension()));
/*  73 */         world.func_184148_a(null, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), SoundsTC.jar, SoundCategory.BLOCKS, 1.0F, 2.0F);
/*  74 */         player.func_145747_a(new TextComponentTranslation("tc.handmirrorlinked", new Object[0]));
/*  75 */         player.field_71069_bz.func_75142_b();
/*     */       } 
/*     */       
/*  78 */       return EnumActionResult.PASS;
/*     */     } 
/*     */     
/*  81 */     return EnumActionResult.FAIL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World world, EntityPlayer player, EnumHand hand) {
/*  87 */     if (!world.field_72995_K && player.func_184586_b(hand).func_77942_o()) {
/*     */       
/*  89 */       int lx = player.func_184586_b(hand).func_77978_p().func_74762_e("linkX");
/*  90 */       int ly = player.func_184586_b(hand).func_77978_p().func_74762_e("linkY");
/*  91 */       int lz = player.func_184586_b(hand).func_77978_p().func_74762_e("linkZ");
/*  92 */       int ldim = player.func_184586_b(hand).func_77978_p().func_74762_e("linkDim");
/*     */       
/*  94 */       WorldServer worldServer = DimensionManager.getWorld(ldim);
/*  95 */       if (worldServer == null) return super.func_77659_a(world, player, hand); 
/*  96 */       TileEntity te = worldServer.func_175625_s(new BlockPos(lx, ly, lz));
/*  97 */       if (te == null || !(te instanceof TileMirror)) {
/*  98 */         player.func_184586_b(hand).func_77982_d(null);
/*  99 */         player.func_184185_a(SoundsTC.zap, 1.0F, 0.8F);
/* 100 */         player.func_145747_a(new TextComponentTranslation("tc.handmirrorerror", new Object[0]));
/* 101 */         return super.func_77659_a(world, player, hand);
/*     */       } 
/*     */       
/* 104 */       player.openGui(Thaumcraft.instance, 4, world, 
/*     */           
/* 106 */           MathHelper.func_76128_c(player.field_70165_t), MathHelper.func_76128_c(player.field_70163_u), MathHelper.func_76128_c(player.field_70161_v));
/*     */     } 
/*     */ 
/*     */     
/* 110 */     return super.func_77659_a(world, player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 116 */     if (stack.func_77942_o()) {
/* 117 */       int lx = stack.func_77978_p().func_74762_e("linkX");
/* 118 */       int ly = stack.func_77978_p().func_74762_e("linkY");
/* 119 */       int lz = stack.func_77978_p().func_74762_e("linkZ");
/* 120 */       int ldim = stack.func_77978_p().func_74762_e("linkDim");
/*     */       
/* 122 */       tooltip.add(I18n.func_74838_a("tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + ldim);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean transport(ItemStack mirror, ItemStack items, EntityPlayer player, World worldObj) {
/* 128 */     if (mirror.func_77942_o()) {
/* 129 */       int lx = mirror.func_77978_p().func_74762_e("linkX");
/* 130 */       int ly = mirror.func_77978_p().func_74762_e("linkY");
/* 131 */       int lz = mirror.func_77978_p().func_74762_e("linkZ");
/* 132 */       int ldim = mirror.func_77978_p().func_74762_e("linkDim");
/* 133 */       WorldServer worldServer = DimensionManager.getWorld(ldim);
/* 134 */       if (worldServer == null) return false; 
/* 135 */       TileEntity te = worldServer.func_175625_s(new BlockPos(lx, ly, lz));
/* 136 */       if (te == null || !(te instanceof TileMirror)) {
/* 137 */         mirror.func_77982_d(null);
/* 138 */         player.func_184185_a(SoundsTC.zap, 1.0F, 0.8F);
/* 139 */         player.func_145747_a(new TextComponentTranslation("tc.handmirrorerror", new Object[0]));
/* 140 */         return false;
/*     */       } 
/* 142 */       TileMirror tm = (TileMirror)te;
/*     */       
/* 144 */       if (tm.transportDirect(items)) {
/* 145 */         items = ItemStack.field_190927_a;
/* 146 */         player.func_184185_a(SoundEvents.field_187534_aX, 0.1F, 1.0F);
/*     */       } 
/* 148 */       return true;
/*     */     } 
/* 150 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemHandMirror.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */