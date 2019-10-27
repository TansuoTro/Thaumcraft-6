/*    */ package thaumcraft.common.items.curios;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.client.util.ITooltipFlag;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.translation.I18n;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCelestialNotes
/*    */   extends ItemTCBase
/*    */ {
/* 18 */   public ItemCelestialNotes() { super("celestial_notes", new String[] { "sun", "stars_1", "stars_2", "stars_3", "stars_4", "moon_1", "moon_2", "moon_3", "moon_4", "moon_5", "moon_6", "moon_7", "moon_8" }); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 23 */   public String func_77667_c(ItemStack itemStack) { return "item.celestial_notes"; }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
/*    */     try {
/* 30 */       tooltip.add(TextFormatting.AQUA + I18n.func_74838_a("item.celestial_notes." + getVariantNames()[stack.func_77952_i()] + ".text"));
/* 31 */     } catch (Exception exception) {}
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\curios\ItemCelestialNotes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */