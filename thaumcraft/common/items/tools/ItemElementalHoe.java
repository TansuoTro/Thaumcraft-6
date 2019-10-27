/*     */ package thaumcraft.common.items.tools;
/*     */ 
/*     */ import net.minecraft.client.renderer.ItemMeshDefinition;
/*     */ import net.minecraft.client.renderer.block.model.ModelResourceLocation;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemHoe;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.client.fx.FXDispatcher;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.IThaumcraftItems;
/*     */ import thaumcraft.common.lib.utils.Utils;
/*     */ 
/*     */ public class ItemElementalHoe
/*     */   extends ItemHoe
/*     */   implements IThaumcraftItems {
/*     */   public ItemElementalHoe(Item.ToolMaterial enumtoolmaterial) {
/*  25 */     super(enumtoolmaterial);
/*  26 */     func_77637_a(ConfigItems.TABTC);
/*  27 */     setRegistryName("elemental_hoe");
/*  28 */     func_77655_b("elemental_hoe");
/*  29 */     ConfigItems.ITEM_VARIANT_HOLDERS.add(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  34 */   public Item getItem() { return this; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public String[] getVariantNames() { return new String[] { "normal" }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   public int[] getVariantMeta() { return new int[] { 0 }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   public ItemMeshDefinition getCustomMesh() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   public ModelResourceLocation getCustomModelResourceLocation(String variant) { return new ModelResourceLocation("thaumcraft:" + variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public int func_77619_b() { return 5; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public boolean func_82789_a(ItemStack stack1, ItemStack stack2) { return stack2.func_77969_a(new ItemStack(ItemsTC.ingots, 1, 0)) ? true : super.func_82789_a(stack1, stack2); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult func_180614_a(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
/*  81 */     if (player.func_70093_af()) return super.func_180614_a(player, world, pos, hand, facing, hitX, hitY, hitZ);
/*     */     
/*  83 */     boolean did = false;
/*  84 */     for (int xx = -1; xx <= 1; xx++) {
/*  85 */       for (int zz = -1; zz <= 1; zz++) {
/*  86 */         if (super.func_180614_a(player, world, pos.func_177982_a(xx, false, zz), hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS) {
/*  87 */           if (world.field_72995_K) {
/*  88 */             BlockPos pp = pos.func_177982_a(xx, 0, zz);
/*  89 */             FXDispatcher.INSTANCE.drawBamf(pp.func_177958_n() + 0.5D, pp.func_177956_o() + 1.01D, pp.func_177952_p() + 0.5D, 0.3F, 0.12F, 0.1F, (xx == 0 && zz == 0), false, EnumFacing.UP);
/*     */           } 
/*  91 */           if (!did) did = true;
/*     */         
/*     */         } 
/*     */       } 
/*     */     } 
/*  96 */     if (!did) {
/*  97 */       did = Utils.useBonemealAtLoc(world, player, pos);
/*  98 */       if (did) {
/*  99 */         player.func_184586_b(hand).func_77972_a(3, player);
/* 100 */         if (!world.field_72995_K) {
/*     */           
/* 102 */           world.func_175669_a(2005, pos, 0);
/*     */         } else {
/* 104 */           FXDispatcher.INSTANCE.drawBlockMistParticles(pos, 4259648);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     return EnumActionResult.SUCCESS;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\tools\ItemElementalHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */