/*     */ package thaumcraft.common.items.consumables;
/*     */ 
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.NonNullList;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.common.config.ConfigItems;
/*     */ import thaumcraft.common.items.ItemTCEssentiaContainer;
/*     */ import thaumcraft.common.tiles.essentia.TileAlembic;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemPhial
/*     */   extends ItemTCEssentiaContainer
/*     */ {
/*  33 */   public ItemPhial() { super("phial", 10, new String[] { "empty", "filled" }); }
/*     */ 
/*     */   
/*     */   public static ItemStack makePhial(Aspect aspect, int amt) {
/*  37 */     ItemStack i = new ItemStack(ItemsTC.phial, 1, 1);
/*  38 */     ((IEssentiaContainerItem)i.func_77973_b()).setAspects(i, (new AspectList()).add(aspect, amt));
/*  39 */     return i;
/*     */   }
/*     */ 
/*     */   
/*  43 */   public static ItemStack makeFilledPhial(Aspect aspect) { return makePhial(aspect, 10); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
/*  48 */     if (tab == ConfigItems.TABTC || tab == CreativeTabs.field_78027_g) {
/*  49 */       items.add(new ItemStack(this, 1, 0));
/*  50 */       for (Aspect tag : Aspect.aspects.values()) {
/*  51 */         ItemStack i = new ItemStack(this, 1, 1);
/*  52 */         setAspects(i, (new AspectList()).add(tag, this.base));
/*  53 */         items.add(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String func_77653_i(ItemStack stack) {
/*  60 */     return (getAspects(stack) != null && !(getAspects(stack)).aspects.isEmpty()) ? 
/*  61 */       String.format(super.func_77653_i(stack), new Object[] { getAspects(stack).getAspects()[0].getName() }) : super
/*  62 */       .func_77653_i(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public String func_77667_c(ItemStack stack) { return func_77658_a() + "." + getVariantNames()[stack.func_77952_i()]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack stack, World world, Entity entity, int par4, boolean par5) {
/*  73 */     if (!world.field_72995_K && !stack.func_77942_o() && stack.func_77952_i() == 1) {
/*  74 */       stack.func_77964_b(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77622_d(ItemStack stack, World world, EntityPlayer player) {
/*  80 */     if (!world.field_72995_K && !stack.func_77942_o() && stack.func_77952_i() == 1) {
/*  81 */       stack.func_77964_b(0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public boolean doesSneakBypassUse(ItemStack stack, IBlockAccess world, BlockPos pos, EntityPlayer player) { return true; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float f1, float f2, float f3, EnumHand hand) {
/*  94 */     IBlockState bi = world.func_180495_p(pos);
/*     */ 
/*     */     
/*  97 */     if (player.func_184586_b(hand).func_77952_i() == 0 && bi.func_177230_c() == BlocksTC.alembic) {
/*  98 */       TileAlembic tile = (TileAlembic)world.func_175625_s(pos);
/*  99 */       if (tile.amount >= this.base) {
/* 100 */         if (world.field_72995_K) {
/* 101 */           player.func_184609_a(hand);
/* 102 */           return EnumActionResult.PASS;
/*     */         } 
/* 104 */         ItemStack phial = new ItemStack(this, 1, 1);
/* 105 */         setAspects(phial, (new AspectList()).add(tile.aspect, this.base));
/* 106 */         if (tile.takeFromContainer(tile.aspect, this.base)) {
/* 107 */           player.func_184586_b(hand).func_190918_g(1);
/* 108 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 109 */             world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, phial));
/*     */           }
/* 111 */           player.func_184185_a(SoundEvents.field_187615_H, 0.25F, 1.0F);
/* 112 */           player.field_71069_bz.func_75142_b();
/* 113 */           return EnumActionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     if (player.func_184586_b(hand).func_77952_i() == 0 && (bi.func_177230_c() == BlocksTC.jarNormal || bi.func_177230_c() == BlocksTC.jarVoid)) {
/* 119 */       TileJarFillable tile = (TileJarFillable)world.func_175625_s(pos);
/* 120 */       if (tile.amount >= this.base) {
/* 121 */         if (world.field_72995_K) {
/* 122 */           player.func_184609_a(hand);
/* 123 */           return EnumActionResult.PASS;
/*     */         } 
/* 125 */         Aspect asp = Aspect.getAspect(tile.aspect.getTag());
/* 126 */         if (tile.takeFromContainer(asp, this.base)) {
/* 127 */           player.func_184586_b(hand).func_190918_g(1);
/* 128 */           ItemStack phial = new ItemStack(this, 1, 1);
/* 129 */           setAspects(phial, (new AspectList()).add(asp, this.base));
/* 130 */           if (!player.field_71071_by.func_70441_a(phial)) {
/* 131 */             world.func_72838_d(new EntityItem(world, (pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F), phial));
/*     */           }
/* 133 */           player.func_184185_a(SoundEvents.field_187615_H, 0.25F, 1.0F);
/* 134 */           player.field_71069_bz.func_75142_b();
/* 135 */           return EnumActionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/* 139 */     AspectList al = getAspects(player.func_184586_b(hand));
/* 140 */     if (al != null && al.size() == 1) {
/* 141 */       Aspect aspect = al.getAspects()[0];
/*     */       
/* 143 */       TileJarFillable tile = (TileJarFillable)world.func_175625_s(pos);
/* 144 */       tile; if (player.func_184586_b(hand).func_77952_i() != 0 && (bi.func_177230_c() == BlocksTC.jarNormal || bi.func_177230_c() == BlocksTC.jarVoid) && tile.amount <= 250 - this.base && tile.doesContainerAccept(aspect)) {
/* 145 */         if (world.field_72995_K) {
/* 146 */           player.func_184609_a(hand);
/* 147 */           return EnumActionResult.PASS;
/*     */         } 
/* 149 */         if (tile.addToContainer(aspect, this.base) == 0) {
/* 150 */           world.markAndNotifyBlock(pos, world.func_175726_f(pos), bi, bi, 3);
/* 151 */           tile.func_70296_d();
/* 152 */           player.func_184586_b(hand).func_190918_g(1);
/* 153 */           if (!player.field_71071_by.func_70441_a(new ItemStack(this, 1, 0))) {
/* 154 */             world.func_72838_d(new EntityItem(world, (pos.func_177958_n() + 0.5F), (pos.func_177956_o() + 0.5F), (pos.func_177952_p() + 0.5F), new ItemStack(this, 1, 0)));
/*     */           }
/* 156 */           player.func_184185_a(SoundEvents.field_187615_H, 0.25F, 1.0F);
/* 157 */           player.field_71069_bz.func_75142_b();
/* 158 */           return EnumActionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 165 */     return EnumActionResult.PASS;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\consumables\ItemPhial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */