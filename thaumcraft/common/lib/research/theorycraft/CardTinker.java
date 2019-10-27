/*    */ package thaumcraft.common.lib.research.theorycraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.TextComponentTranslation;
/*    */ import thaumcraft.api.items.ItemsTC;
/*    */ import thaumcraft.api.research.theorycraft.ResearchTableData;
/*    */ import thaumcraft.api.research.theorycraft.TheorycraftCard;
/*    */ import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
/*    */ 
/*    */ public class CardTinker
/*    */   extends TheorycraftCard
/*    */ {
/* 19 */   ItemStack stack = ItemStack.field_190927_a;
/*    */   static ItemStack[] options = { 
/* 21 */       new ItemStack(ItemsTC.visResonator), new ItemStack(ItemsTC.thaumometer), new ItemStack(Blocks.field_150467_bQ), new ItemStack(Blocks.field_150408_cc), new ItemStack(Blocks.field_150367_z), new ItemStack(Blocks.field_150409_cd), new ItemStack(Blocks.field_150381_bn), new ItemStack(Blocks.field_150477_bB), new ItemStack(Blocks.field_150421_aI), new ItemStack(Blocks.field_150453_bW), new ItemStack(Blocks.field_150331_J), new ItemStack(Blocks.field_150438_bZ), new ItemStack(Blocks.field_150320_F), new ItemStack(Items.field_151148_bJ), new ItemStack(Items.field_151111_aL), new ItemStack(Items.field_151142_bV), new ItemStack(Items.field_151132_bS), new ItemStack(Items.field_151113_aN) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NBTTagCompound serialize() {
/* 37 */     NBTTagCompound nbt = super.serialize();
/* 38 */     nbt.func_74782_a("stack", this.stack.serializeNBT());
/* 39 */     return nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void deserialize(NBTTagCompound nbt) {
/* 44 */     super.deserialize(nbt);
/* 45 */     this.stack = new ItemStack(nbt.func_74775_l("stack"));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean initialize(EntityPlayer player, ResearchTableData data) {
/* 50 */     Random r = new Random(getSeed());
/* 51 */     this.stack = options[r.nextInt(options.length)].func_77946_l();
/* 52 */     return (this.stack != null);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public int getInspirationCost() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String getResearchCategory() { return "ARTIFICE"; }
/*    */ 
/*    */   
/*    */   private int getVal() {
/* 66 */     int q = 0;
/*    */     try {
/* 68 */       q = (int)(q + Math.sqrt(ThaumcraftCraftingManager.getObjectTags(this.stack).visSize()));
/* 69 */     } catch (Exception exception) {}
/* 70 */     return q;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public String getLocalizedName() { return (new TextComponentTranslation("card.tinker.name", new Object[0])).func_150254_d(); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedText() {
/* 80 */     int a = getVal() * 2;
/* 81 */     int b = a + 10;
/* 82 */     return (new TextComponentTranslation("card.tinker.text", new Object[] {
/* 83 */           Integer.valueOf(a), Integer.valueOf(b) })).func_150254_d();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 88 */   public ItemStack[] getRequiredItems() { return new ItemStack[] { this.stack }; }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean activate(EntityPlayer player, ResearchTableData data) {
/* 93 */     int q = getVal() * 2;
/* 94 */     data.addTotal(getResearchCategory(), MathHelper.func_76136_a(player.func_70681_au(), q, q + 10));
/* 95 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\research\theorycraft\CardTinker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */