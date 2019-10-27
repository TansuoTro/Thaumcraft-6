/*    */ package thaumcraft.common.items.baubles;
/*    */ 
/*    */ import baubles.api.BaubleType;
/*    */ import baubles.api.IBauble;
/*    */ import baubles.api.render.IRenderBauble;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.GlStateManager;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.EntityEquipmentSlot;
/*    */ import net.minecraft.item.EnumRarity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.client.lib.UtilsFX;
/*    */ import thaumcraft.common.items.ItemTCBase;
/*    */ 
/*    */ public class ItemCuriosityBand
/*    */   extends ItemTCBase implements IBauble, IRenderBauble {
/*    */   ResourceLocation tex;
/*    */   
/*    */   public ItemCuriosityBand() {
/* 23 */     super("curiosity_band", new String[0]);
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
/*    */     
/* 39 */     this.tex = new ResourceLocation("thaumcraft", "textures/items/curiosity_band_worn.png");
/*    */     this.field_77777_bU = 1;
/*    */     this.canRepair = false;
/*    */     func_77656_e(0); } @SideOnly(Side.CLIENT)
/*    */   public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, IRenderBauble.RenderType type, float ticks) {
/* 44 */     if (type == IRenderBauble.RenderType.HEAD) {
/* 45 */       boolean armor = !player.func_184582_a(EntityEquipmentSlot.HEAD).func_190926_b();
/* 46 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(this.tex);
/* 47 */       IRenderBauble.Helper.translateToHeadLevel(player);
/* 48 */       IRenderBauble.Helper.translateToFace();
/* 49 */       IRenderBauble.Helper.defaultTransforms();
/* 50 */       GlStateManager.func_179114_b(180.0F, 0.0F, 1.0F, 0.0F);
/* 51 */       GlStateManager.func_179137_b(-0.5D, -0.5D, armor ? 0.11999999731779099D : 0.0D);
/* 52 */       UtilsFX.renderTextureIn3D(0.0F, 0.0F, 1.0F, 1.0F, 16, 26, 0.1F);
/*    */     } 
/*    */   }
/*    */   
/*    */   public EnumRarity func_77613_e(ItemStack itemstack) { return EnumRarity.RARE; }
/*    */   
/*    */   public BaubleType getBaubleType(ItemStack itemstack) { return BaubleType.HEAD; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\items\baubles\ItemCuriosityBand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */