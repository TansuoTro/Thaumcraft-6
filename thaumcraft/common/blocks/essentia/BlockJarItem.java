/*     */ package thaumcraft.common.blocks.essentia;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.client.util.ITooltipFlag;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.SoundEvents;
/*     */ import net.minecraft.item.IItemPropertyGetter;
/*     */ import net.minecraft.item.ItemBlock;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumActionResult;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.EnumHand;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.aspects.AspectList;
/*     */ import thaumcraft.api.aspects.IEssentiaContainerItem;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.common.tiles.essentia.TileAlembic;
/*     */ import thaumcraft.common.tiles.essentia.TileJarFillable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockJarItem
/*     */   extends ItemBlock
/*     */   implements IEssentiaContainerItem
/*     */ {
/*     */   public BlockJarItem(Block block) {
/*  39 */     super(block);
/*     */     
/*  41 */     func_185043_a(new ResourceLocation("fill"), new IItemPropertyGetter()
/*     */         {
/*     */           @SideOnly(Side.CLIENT)
/*     */           public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
/*     */           {
/*  46 */             if (stack.func_77973_b().getDurabilityForDisplay(stack) == 1.0D)
/*     */             {
/*  48 */               return 0.0F;
/*     */             }
/*     */             
/*  51 */             if (stack.func_77973_b().getDurabilityForDisplay(stack) >= 0.75D)
/*     */             {
/*  53 */               return 1.0F;
/*     */             }
/*  55 */             if (stack.func_77973_b().getDurabilityForDisplay(stack) >= 0.5D)
/*     */             {
/*  57 */               return 2.0F;
/*     */             }
/*     */             
/*  60 */             if (stack.func_77973_b().getDurabilityForDisplay(stack) >= 0.25D)
/*     */             {
/*  62 */               return 3.0F;
/*     */             }
/*     */ 
/*     */             
/*  66 */             return 4.0F;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public boolean showDurabilityBar(ItemStack stack) { return (getAspects(stack) != null); }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDurabilityForDisplay(ItemStack stack) {
/*  81 */     AspectList al = getAspects(stack);
/*  82 */     return (al == null) ? 1.0D : (1.0D - al.visSize() / 250.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
/*  89 */     Block bi = world.func_180495_p(pos).func_177230_c();
/*  90 */     ItemStack itemstack = player.func_184586_b(hand);
/*     */ 
/*     */     
/*  93 */     if (bi == BlocksTC.alembic && !world.field_72995_K) {
/*  94 */       TileAlembic tile = (TileAlembic)world.func_175625_s(pos);
/*  95 */       if (tile.amount > 0) {
/*  96 */         if (getFilter(itemstack) != null && getFilter(itemstack) != tile.aspect) return EnumActionResult.FAIL; 
/*  97 */         if (getAspects(itemstack) != null && getAspects(itemstack).getAspects()[false] != tile.aspect) return EnumActionResult.FAIL;
/*     */ 
/*     */         
/* 100 */         int amt = tile.amount;
/* 101 */         if (getAspects(itemstack) != null && getAspects(itemstack).visSize() + amt > 250) amt = Math.abs(getAspects(itemstack).visSize() - 250); 
/* 102 */         if (amt <= 0) return EnumActionResult.FAIL; 
/* 103 */         Aspect a = tile.aspect;
/* 104 */         if (tile.takeFromContainer(tile.aspect, amt)) {
/* 105 */           int base = (getAspects(itemstack) == null) ? 0 : getAspects(itemstack).visSize();
/* 106 */           if (itemstack.func_190916_E() > 1) {
/* 107 */             ItemStack stack = itemstack.func_77946_l();
/* 108 */             setAspects(stack, (new AspectList()).add(a, base + amt));
/* 109 */             itemstack.func_190918_g(1);
/* 110 */             stack.func_190920_e(1);
/* 111 */             if (!player.field_71071_by.func_70441_a(stack)) {
/* 112 */               world.func_72838_d(new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, stack));
/*     */             }
/*     */           } else {
/* 115 */             setAspects(itemstack, (new AspectList()).add(a, base + amt));
/*     */           } 
/*     */           
/* 118 */           player.func_184185_a(SoundEvents.field_187615_H, 0.25F, 1.0F);
/* 119 */           player.field_71069_bz.func_75142_b();
/* 120 */           return EnumActionResult.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/* 124 */     return EnumActionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
/* 132 */     boolean b = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);
/*     */     
/* 134 */     if (b && !world.field_72995_K) {
/* 135 */       TileEntity te = world.func_175625_s(pos);
/* 136 */       if (te != null && te instanceof TileJarFillable) {
/* 137 */         TileJarFillable jar = (TileJarFillable)te;
/* 138 */         jar.setAspects(getAspects(stack));
/* 139 */         if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("AspectFilter")) {
/* 140 */           jar.aspectFilter = Aspect.getAspect(stack.func_77978_p().func_74779_i("AspectFilter"));
/*     */         }
/* 142 */         te.func_70296_d();
/* 143 */         world.markAndNotifyBlock(pos, world.func_175726_f(pos), newState, newState, 3);
/*     */       } 
/*     */     } 
/*     */     
/* 147 */     return b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/* 155 */     if (stack.func_77942_o() && stack.func_77978_p().func_74764_b("AspectFilter")) {
/* 156 */       String tf = stack.func_77978_p().func_74779_i("AspectFilter");
/* 157 */       Aspect tag = Aspect.getAspect(tf);
/* 158 */       tooltip.add("§5" + tag.getName());
/*     */     } 
/* 160 */     super.func_77624_a(stack, worldIn, tooltip, flagIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public AspectList getAspects(ItemStack itemstack) {
/* 165 */     if (itemstack.func_77942_o()) {
/* 166 */       AspectList aspects = new AspectList();
/* 167 */       aspects.readFromNBT(itemstack.func_77978_p());
/* 168 */       return (aspects.size() > 0) ? aspects : null;
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */   
/*     */   public Aspect getFilter(ItemStack itemstack) {
/* 174 */     if (itemstack.func_77942_o()) {
/* 175 */       return Aspect.getAspect(itemstack.func_77978_p().func_74779_i("AspectFilter"));
/*     */     }
/* 177 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAspects(ItemStack itemstack, AspectList aspects) {
/* 182 */     if (!itemstack.func_77942_o()) itemstack.func_77982_d(new NBTTagCompound()); 
/* 183 */     aspects.writeToNBT(itemstack.func_77978_p());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   public boolean ignoreContainedAspects() { return false; }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\blocks\essentia\BlockJarItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */