/*     */ package thaumcraft.common.golems;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagLong;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.translation.I18n;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.golems.EnumGolemTrait;
/*     */ import thaumcraft.api.golems.IGolemProperties;
/*     */ import thaumcraft.api.golems.ISealDisplayer;
/*     */ import thaumcraft.api.golems.parts.GolemArm;
/*     */ import thaumcraft.api.golems.parts.GolemHead;
/*     */ import thaumcraft.api.golems.parts.GolemMaterial;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ 
/*     */ public class ItemGolemPlacer
/*     */   extends ItemTCBase
/*     */   implements ISealDisplayer
/*     */ {
/*  33 */   public ItemGolemPlacer() { super("golem", new String[0]); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  38 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/*  39 */       ItemStack is = new ItemStack(this, 1, 0);
/*  40 */       is.func_77983_a("props", new NBTTagLong(0L));
/*  41 */       items.add(is.func_77946_l());
/*  42 */       IGolemProperties props = new GolemProperties();
/*  43 */       props.setHead(GolemHead.getHeads()[1]);
/*  44 */       props.setArms(GolemArm.getArms()[1]);
/*  45 */       is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  46 */       items.add(is.func_77946_l());
/*     */       
/*  48 */       props = new GolemProperties();
/*  49 */       props.setMaterial(GolemMaterial.getMaterials()[1]);
/*  50 */       props.setHead(GolemHead.getHeads()[1]);
/*  51 */       props.setArms(GolemArm.getArms()[2]);
/*  52 */       is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  53 */       items.add(is.func_77946_l());
/*     */       
/*  55 */       props = new GolemProperties();
/*  56 */       props.setMaterial(GolemMaterial.getMaterials()[4]);
/*  57 */       props.setHead(GolemHead.getHeads()[1]);
/*  58 */       props.setArms(GolemArm.getArms()[3]);
/*  59 */       is.func_77983_a("props", new NBTTagLong(props.toLong()));
/*  60 */       items.add(is.func_77946_l());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*  67 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("props")) {
/*  68 */       IGolemProperties props = GolemProperties.fromLong(stack.func_77978_p().func_74763_f("props"));
/*     */       
/*  70 */       if (props.hasTrait(EnumGolemTrait.SMART)) {
/*  71 */         if (props.getRank() >= 10) {
/*  72 */           tooltip.add("§6" + I18n.func_74838_a("golem.rank") + " " + props.getRank());
/*     */         } else {
/*  74 */           int rx = stack.func_77978_p().func_74762_e("xp");
/*  75 */           int xn = (props.getRank() + 1) * (props.getRank() + 1) * 1000;
/*  76 */           tooltip.add("§6" + I18n.func_74838_a("golem.rank") + " " + props.getRank() + " §2(" + rx + "/" + xn + ")");
/*     */         } 
/*     */       }
/*     */       
/*  80 */       tooltip.add("§a" + props.getMaterial().getLocalizedName());
/*     */       
/*  82 */       for (EnumGolemTrait tag : props.getTraits()) {
/*  83 */         tooltip.add("§9-" + tag.getLocalizedName());
/*     */       }
/*     */     } 
/*  86 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/*  94 */     IBlockState bs = world.func_180495_p(pos);
/*     */     
/*  96 */     if (!bs.func_185904_a().func_76220_a()) return EnumActionResult.FAIL; 
/*  97 */     if (world.field_72995_K) return EnumActionResult.PASS;
/*     */     
/*  99 */     pos = pos.func_177972_a(side);
/* 100 */     bs = world.func_180495_p(pos);
/*     */     
/* 102 */     if (!player.func_175151_a(pos, side, player.func_184586_b(hand))) return EnumActionResult.FAIL;
/*     */     
/* 104 */     EntityThaumcraftGolem golem = new EntityThaumcraftGolem(world);
/* 105 */     golem.func_70080_a(pos.func_177958_n() + 0.5D, pos.func_177956_o(), pos.func_177952_p() + 0.5D, 0.0F, 0.0F);
/* 106 */     if (golem != null && world.func_72838_d(golem)) {
/* 107 */       golem.setOwned(true);
/* 108 */       golem.setValidSpawn();
/* 109 */       golem.setOwnerId(player.func_110124_au());
/* 110 */       if (player.func_184586_b(hand).func_77942_o() && player.func_184586_b(hand).func_77978_p().func_74764_b("props")) {
/* 111 */         golem.setProperties(GolemProperties.fromLong(player.func_184586_b(hand).func_77978_p().func_74763_f("props")));
/*     */       }
/* 113 */       if (player.func_184586_b(hand).func_77942_o() && player.func_184586_b(hand).func_77978_p().func_74764_b("xp")) {
/* 114 */         golem.rankXp = player.func_184586_b(hand).func_77978_p().func_74762_e("xp");
/*     */       }
/* 116 */       golem.func_180482_a(world.func_175649_E(pos), (IEntityLivingData)null);
/* 117 */       if (!player.field_71075_bZ.field_75098_d) player.func_184586_b(hand).func_190918_g(1);
/*     */     
/*     */     } 
/*     */     
/* 121 */     return EnumActionResult.SUCCESS;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\golems\ItemGolemPlacer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */