/*     */ package thaumcraft.common.items.curios;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.EnumRarity;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.common.items.ItemTCBase;
/*     */ 
/*     */ 
/*     */ public class ItemPrimordialPearl
/*     */   extends ItemTCBase
/*     */ {
/*     */   public ItemPrimordialPearl() {
/*  20 */     super("primordial_pearl", new String[0]);
/*  21 */     this.field_77777_bU = 1;
/*     */     
/*  23 */     func_77656_e(8);
/*     */     
/*  25 */     func_185043_a(new ResourceLocation("type"), new IItemPropertyGetter()
/*     */         {
/*     */           @SideOnly(Side.CLIENT)
/*     */           public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
/*     */           {
/*  30 */             if (stack.func_77952_i() < 3)
/*     */             {
/*  32 */               return 0.0F;
/*     */             }
/*     */             
/*  35 */             if (stack.func_77952_i() < 6)
/*     */             {
/*  37 */               return 1.0F;
/*     */             }
/*     */ 
/*     */             
/*  41 */             return 2.0F;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*  46 */     setNoRepair();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public boolean func_82789_a(ItemStack toRepair, ItemStack repair) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public boolean isRepairable() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_77667_c(ItemStack stack) {
/*  63 */     if (stack.func_77952_i() < 3)
/*     */     {
/*  65 */       return func_77658_a() + ".pearl";
/*     */     }
/*     */     
/*  68 */     if (stack.func_77952_i() < 6)
/*     */     {
/*  70 */       return func_77658_a() + ".nodule";
/*     */     }
/*     */ 
/*     */     
/*  74 */     return func_77658_a() + ".mote";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getContainerItem(ItemStack itemStack) {
/*  80 */     if (!hasContainerItem(itemStack))
/*     */     {
/*  82 */       return ItemStack.field_190927_a;
/*     */     }
/*  84 */     return new ItemStack(itemStack.func_77973_b(), itemStack.func_190916_E(), itemStack.func_77952_i() + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public boolean hasContainerItem(ItemStack stack) { return (stack.func_77952_i() < 7); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  94 */   public EnumRarity func_77613_e(ItemStack stack) { return EnumRarity.UNCOMMON; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public boolean func_77616_k(ItemStack stack) { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemPrimordialPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */