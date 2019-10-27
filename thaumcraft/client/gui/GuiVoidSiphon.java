/*    */ package thaumcraft.client.gui;
/*    */ 
/*    */ import com.sasmaster.glelwjgl.java.CoreGLE;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.inventory.GuiContainer;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import org.lwjgl.opengl.ARBShaderObjects;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ import thaumcraft.client.lib.ender.ShaderCallback;
/*    */ import thaumcraft.common.container.ContainerVoidSiphon;
/*    */ import thaumcraft.common.tiles.crafting.TileVoidSiphon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiVoidSiphon
/*    */   extends GuiContainer
/*    */ {
/*    */   private TileVoidSiphon inventory;
/* 28 */   private ContainerVoidSiphon container = null;
/* 29 */   private EntityPlayer player = null;
/*    */ 
/*    */   
/* 32 */   CoreGLE gle = new CoreGLE();
/*    */   private final ShaderCallback shaderCallback;
/* 34 */   private static final ResourceLocation starsTexture = new ResourceLocation("textures/entity/end_portal.png");
/*    */   ResourceLocation tex;
/*    */   
/*    */   public GuiVoidSiphon(InventoryPlayer par1InventoryPlayer, TileVoidSiphon tileVoidSiphon) {
/* 38 */     super(new ContainerVoidSiphon(par1InventoryPlayer, tileVoidSiphon));
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 59 */     this.tex = new ResourceLocation("thaumcraft", "textures/gui/gui_void_siphon.png"); this.field_146999_f = 176; this.field_147000_g = 166; this.inventory = tileVoidSiphon; this.container = (ContainerVoidSiphon)this.field_147002_h; this.player = par1InventoryPlayer.field_70458_d; this.shaderCallback = new ShaderCallback() { public void call(int shader) { Minecraft mc = Minecraft.func_71410_x();
/*    */           int x = ARBShaderObjects.glGetUniformLocationARB(shader, "yaw");
/*    */           ARBShaderObjects.glUniform1fARB(x, (float)((mc.field_71439_g.field_70177_z * 2.0F) * Math.PI / 360.0D));
/*    */           int z = ARBShaderObjects.glGetUniformLocationARB(shader, "pitch");
/*    */           ARBShaderObjects.glUniform1fARB(z, -((float)((mc.field_71439_g.field_70125_A * 2.0F) * Math.PI / 360.0D))); } };
/* 64 */   } public void func_73863_a(int mouseX, int mouseY, float partialTicks) { func_146276_q_();
/* 65 */     super.func_73863_a(mouseX, mouseY, partialTicks);
/* 66 */     func_191948_b(mouseX, mouseY); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int mx, int my) {
/* 74 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 75 */     this.field_146297_k.field_71446_o.func_110577_a(this.tex);
/* 76 */     int k = (this.field_146294_l - this.field_146999_f) / 2;
/* 77 */     int l = (this.field_146295_m - this.field_147000_g) / 2;
/*    */     
/* 79 */     GL11.glEnable(3042);
/* 80 */     func_73729_b(k, l, 0, 0, this.field_146999_f, this.field_147000_g);
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\gui\GuiVoidSiphon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */