/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.ItemSword;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.EnumParticleTypes;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.SoundsTC;
/*     */ import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
/*     */ import thaumcraft.common.lib.utils.EntityUtils;
/*     */ 
/*     */ public class ItemElementalSword
/*     */   extends ItemSword
/*     */   implements IThaumcraftItems
/*     */ {
/*     */   public ItemElementalSword(Item.ToolMaterial enumtoolmaterial) {
/*  37 */     super(enumtoolmaterial);
/*  38 */     func_77637_a(ConfigItems.TABTC);
/*  39 */     setRegistryName("elemental_sword");
/*  40 */     func_77655_b("elemental_sword");
/*  41 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  46 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  71 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/*  72 */       ItemStack w1 = new ItemStack(this);
/*  73 */       EnumInfusionEnchantment.addInfusionEnchantment(w1, EnumInfusionEnchantment.ARCING, 2);
/*  74 */       items.add(w1);
/*     */     } else {
/*  76 */       super.func_150895_a(tab, items);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public EnumAction func_77661_b(ItemStack stack) { return EnumAction.NONE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public int func_77626_a(ItemStack stack) { return 72000; }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand hand) {
/* 105 */     playerIn.func_184598_c(hand);
/* 106 */     return new ActionResult(EnumActionResult.SUCCESS, playerIn.func_184586_b(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
/* 111 */     super.onUsingTick(stack, player, count);
/* 112 */     int ticks = func_77626_a(stack) - count;
/* 113 */     if (player.field_70181_x < 0.0D) {
/* 114 */       player.field_70181_x /= 1.2000000476837158D;
/* 115 */       player.field_70143_R /= 1.2F;
/*     */     } 
/*     */     
/* 118 */     player.field_70181_x += 0.07999999821186066D;
/* 119 */     if (player.field_70181_x > 0.5D) player.field_70181_x = 0.20000000298023224D; 
/* 120 */     if (player instanceof EntityPlayerMP) {
/* 121 */       EntityUtils.resetFloatCounter((EntityPlayerMP)player);
/*     */     }
/*     */     
/* 124 */     List targets = player.field_70170_p.func_72839_b(player, player
/* 125 */         .func_174813_aQ().func_72314_b(2.5D, 2.5D, 2.5D));
/* 126 */     if (targets.size() > 0) {
/* 127 */       for (int var9 = 0; var9 < targets.size(); var9++) {
/*     */         
/* 129 */         Entity entity = (Entity)targets.get(var9);
/* 130 */         if (!(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase && 
/* 131 */           !entity.field_70128_L && (
/* 132 */           player.func_184187_bx() == null || player.func_184187_bx() != entity)) {
/*     */           
/* 134 */           Vec3d p = new Vec3d(player.field_70165_t, player.field_70163_u, player.field_70161_v);
/* 135 */           Vec3d t = new Vec3d(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/* 136 */           double distance = p.func_72438_d(t) + 0.1D;
/*     */           
/* 138 */           Vec3d r = new Vec3d(t.field_72450_a - p.field_72450_a, t.field_72448_b - p.field_72448_b, t.field_72449_c - p.field_72449_c);
/*     */           
/* 140 */           entity.field_70159_w += r.field_72450_a / 2.5D / distance;
/* 141 */           entity.field_70181_x += r.field_72448_b / 2.5D / distance;
/* 142 */           entity.field_70179_y += r.field_72449_c / 2.5D / distance;
/*     */         } 
/*     */       } 
/*     */     }
/* 146 */     if (player.field_70170_p.field_72995_K) {
/* 147 */       int miny = (int)((player.func_174813_aQ()).field_72338_b - 2.0D);
/* 148 */       if (player.field_70122_E) miny = MathHelper.func_76128_c((player.func_174813_aQ()).field_72338_b); 
/* 149 */       for (int a = 0; a < 5; a++) {
/* 150 */         FXDispatcher.INSTANCE.smokeSpiral(player.field_70165_t, 
/* 151 */             (player.func_174813_aQ()).field_72338_b + (player.field_70131_O / 2.0F), player.field_70161_v, 1.5F, player.field_70170_p.field_73012_v
/* 152 */             .nextInt(360), miny, 14540253);
/*     */       }
/* 154 */       if (player.field_70122_E) {
/* 155 */         float r1 = player.field_70170_p.field_73012_v.nextFloat() * 360.0F;
/* 156 */         float mx = -MathHelper.func_76126_a(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 157 */         float mz = MathHelper.func_76134_b(r1 / 180.0F * 3.1415927F) / 5.0F;
/* 158 */         player.field_70170_p.func_175688_a(EnumParticleTypes.SMOKE_NORMAL, player.field_70165_t, 
/* 159 */             (player.func_174813_aQ()).field_72338_b + 0.10000000149011612D, player.field_70161_v, mx, 0.0D, mz, new int[0]);
/*     */       }
/*     */     
/*     */     }
/* 163 */     else if (ticks == 0 || ticks % 20 == 0) {
/* 164 */       player.func_184185_a(SoundsTC.wind, 0.5F, 0.9F + player.field_70170_p.field_73012_v.nextFloat() * 0.2F);
/*     */     } 
/*     */ 
/*     */     
/* 168 */     if (ticks % 20 == 0) stack.func_77972_a(1, player); 
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemElementalSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */