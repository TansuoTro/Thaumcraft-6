/*     */ package thaumcraft.common.blocks.devices;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagInt;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraftforge.common.DimensionManager;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.tiles.devices.TileMirror;
/*     */ import thaumcraft.common.tiles.devices.TileMirrorEssentia;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockMirrorItem
/*     */   extends ItemBlock
/*     */ {
/*  35 */   public BlockMirrorItem(Block par1) { super(par1); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/*  45 */     if (world.func_180495_p(pos).func_177230_c() instanceof BlockMirror) {
/*  46 */       if (world.field_72995_K) {
/*  47 */         player.func_184609_a(hand);
/*  48 */         return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*     */       } 
/*  50 */       if (this.field_150939_a == BlocksTC.mirror) {
/*  51 */         TileEntity tm = world.func_175625_s(pos);
/*  52 */         if (tm != null && tm instanceof TileMirror && !((TileMirror)tm).isLinkValid()) {
/*  53 */           ItemStack st = player.func_184586_b(hand).func_77946_l();
/*  54 */           st.func_190920_e(1);
/*  55 */           st.func_77964_b(1);
/*  56 */           st.func_77983_a("linkX", new NBTTagInt(tm.func_174877_v().func_177958_n()));
/*  57 */           st.func_77983_a("linkY", new NBTTagInt(tm.func_174877_v().func_177956_o()));
/*  58 */           st.func_77983_a("linkZ", new NBTTagInt(tm.func_174877_v().func_177952_p()));
/*  59 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.getDimension()));
/*  60 */           world.func_184133_a(null, pos, SoundsTC.jar, SoundCategory.BLOCKS, 1.0F, 2.0F);
/*  61 */           if (!player.field_71071_by.func_70441_a(st) && 
/*  62 */             !world.field_72995_K) {
/*  63 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/*  66 */           if (!player.field_71075_bZ.field_75098_d) {
/*  67 */             player.func_184586_b(hand).func_190918_g(1);
/*     */           }
/*  69 */           player.field_71069_bz.func_75142_b();
/*  70 */         } else if (tm != null && tm instanceof TileMirror) {
/*  71 */           player.func_145747_a(new TextComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         } 
/*     */       } else {
/*  74 */         TileEntity tm = world.func_175625_s(pos);
/*  75 */         if (tm != null && tm instanceof TileMirrorEssentia && !((TileMirrorEssentia)tm).isLinkValid()) {
/*  76 */           ItemStack st = player.func_184586_b(hand).func_77946_l();
/*  77 */           st.func_190920_e(1);
/*  78 */           st.func_77964_b(1);
/*  79 */           st.func_77983_a("linkX", new NBTTagInt(tm.func_174877_v().func_177958_n()));
/*  80 */           st.func_77983_a("linkY", new NBTTagInt(tm.func_174877_v().func_177956_o()));
/*  81 */           st.func_77983_a("linkZ", new NBTTagInt(tm.func_174877_v().func_177952_p()));
/*  82 */           st.func_77983_a("linkDim", new NBTTagInt(world.field_73011_w.getDimension()));
/*  83 */           world.func_184133_a(null, pos, SoundsTC.jar, SoundCategory.BLOCKS, 1.0F, 2.0F);
/*  84 */           if (!player.field_71071_by.func_70441_a(st) && 
/*  85 */             !world.field_72995_K) {
/*  86 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, st));
/*     */           }
/*     */           
/*  89 */           if (!player.field_71075_bZ.field_75098_d) {
/*  90 */             player.func_184586_b(hand).func_190918_g(1);
/*     */           }
/*  92 */           player.field_71069_bz.func_75142_b();
/*  93 */         } else if (tm != null && tm instanceof TileMirrorEssentia) {
/*  94 */           player.func_145747_a(new TextComponentTranslation("§5§oThat mirror is already linked to a valid destination.", new Object[0]));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  99 */     return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
/* 108 */     boolean ret = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
/* 109 */     if (ret && !world.field_72995_K) {
/* 110 */       if (this.field_150939_a == BlocksTC.mirror) {
/* 111 */         TileEntity te = world.func_175625_s(pos);
/* 112 */         if (te != null && te instanceof TileMirror && 
/* 113 */           stack.func_77942_o()) {
/* 114 */           ((TileMirror)te).linkX = stack.func_77978_p().func_74762_e("linkX");
/* 115 */           ((TileMirror)te).linkY = stack.func_77978_p().func_74762_e("linkY");
/* 116 */           ((TileMirror)te).linkZ = stack.func_77978_p().func_74762_e("linkZ");
/* 117 */           ((TileMirror)te).linkDim = stack.func_77978_p().func_74762_e("linkDim");
/* 118 */           ((TileMirror)te).restoreLink();
/*     */         } 
/*     */       } else {
/*     */         
/* 122 */         TileEntity te = world.func_175625_s(pos);
/* 123 */         if (te != null && te instanceof TileMirrorEssentia && 
/* 124 */           stack.func_77942_o()) {
/* 125 */           ((TileMirrorEssentia)te).linkX = stack.func_77978_p().func_74762_e("linkX");
/* 126 */           ((TileMirrorEssentia)te).linkY = stack.func_77978_p().func_74762_e("linkY");
/* 127 */           ((TileMirrorEssentia)te).linkZ = stack.func_77978_p().func_74762_e("linkZ");
/* 128 */           ((TileMirrorEssentia)te).linkDim = stack.func_77978_p().func_74762_e("linkDim");
/* 129 */           ((TileMirrorEssentia)te).restoreLink();
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 134 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack item, World worldIn, List<String> list, ITooltipFlag flagIn) {
/* 147 */     if (item.func_77942_o()) {
/* 148 */       int lx = item.func_77978_p().func_74762_e("linkX");
/* 149 */       int ly = item.func_77978_p().func_74762_e("linkY");
/* 150 */       int lz = item.func_77978_p().func_74762_e("linkZ");
/* 151 */       int ldim = item.func_77978_p().func_74762_e("linkDim");
/* 152 */       String desc = "" + ldim;
/* 153 */       WorldServer worldServer = DimensionManager.getWorld(ldim);
/* 154 */       if (worldServer != null) {
/* 155 */         desc = worldServer.field_73011_w.func_186058_p().func_186065_b();
/*     */       }
/* 157 */       list.add("Linked to " + lx + "," + ly + "," + lz + " in " + desc);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\devices\BlockMirrorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */