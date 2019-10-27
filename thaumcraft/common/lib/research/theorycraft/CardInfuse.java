/*     */ package thaumcraft.common.lib.research.theorycraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.EnumDyeColor;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.text.TextComponentTranslation;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import thaumcraft.api.aspects.Aspect;
/*     */ import thaumcraft.api.blocks.BlocksTC;
/*     */ import thaumcraft.api.items.ItemsTC;
/*     */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*     */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*     */ import thaumcraft.common.items.consumables.ItemPhial;
/*     */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*     */ 
/*     */ public class CardInfuse
/*     */   extends TheorycraftCard {
/*     */   Aspect aspect;
/*  24 */   ItemStack stack = ItemStack.field_190927_a;
/*     */   static ItemStack[] options = { 
/*  26 */       new ItemStack(ItemsTC.alumentum), new ItemStack((Block)BlocksTC.nitor
/*  27 */         .get(EnumDyeColor.YELLOW)), new ItemStack(ItemsTC.amber), new ItemStack(ItemsTC.brain), new ItemStack(ItemsTC.fabric), new ItemStack(ItemsTC.salisMundus), new ItemStack(ItemsTC.ingots, 1, 0), new ItemStack(ItemsTC.ingots, 1, 2), new ItemStack(ItemsTC.quicksilver), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151045_i), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151072_bj), new ItemStack(Items.field_151116_aA), new ItemStack(Blocks.field_150325_L), new ItemStack(Items.field_151118_aC), new ItemStack(Items.field_151032_g), new ItemStack(Items.field_151110_aK), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151114_aO), new ItemStack(Items.field_151137_ax), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151016_H), new ItemStack(Items.field_151031_f), new ItemStack(Items.field_151010_B), new ItemStack(Items.field_151040_l), new ItemStack(Items.field_151035_b), new ItemStack(Items.field_151005_D), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151034_e) };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound serialize() {
/*  47 */     NBTTagCompound nbt = super.serialize();
/*  48 */     nbt.func_74778_a("aspect", this.aspect.getTag());
/*  49 */     nbt.func_74782_a("stack", this.stack.serializeNBT());
/*  50 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void deserialize(NBTTagCompound nbt) {
/*  55 */     super.deserialize(nbt);
/*  56 */     this.aspect = Aspect.getAspect(nbt.func_74779_i("aspect"));
/*  57 */     this.stack = new ItemStack(nbt.func_74775_l("stack"));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/*  62 */     Random r = new Random(getSeed());
/*  63 */     int num = r.nextInt(Aspect.getCompoundAspects().size());
/*  64 */     this.aspect = (Aspect)Aspect.getCompoundAspects().get(num);
/*  65 */     this.stack = options[r.nextInt(options.length)].func_77946_l();
/*  66 */     return (this.aspect != null && this.stack != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  71 */   public int getInspirationCost() { return 1; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public String getResearchCategory() { return "INFUSION"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public String getLocalizedName() { return (new TextComponentTranslation("card.infuse.name", new Object[0])).func_150254_d(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public String getLocalizedText() { return (new TextComponentTranslation("card.infuse.text", new Object[] { TextFormatting.BOLD + this.aspect
/*     */           
/*  88 */           .getName() + TextFormatting.RESET, this.stack.func_82833_r(), Integer.valueOf(getVal())
/*  89 */         })).func_150254_d(); }
/*     */ 
/*     */   
/*     */   private int getVal() {
/*  93 */     int q = 10;
/*     */     try {
/*  95 */       q = (int)(q + Math.sqrt(ThaumcraftCraftingManager.getObjectTags(this.stack).visSize()) * 1.5D);
/*  96 */     } catch (Exception exception) {}
/*  97 */     return q;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { this.stack, ItemPhial.makeFilledPhial(this.aspect) }; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean[] getRequiredItemsConsumed() { return new boolean[] { true, true }; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 112 */     data.addTotal(getResearchCategory(), getVal());
/* 113 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardInfuse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */