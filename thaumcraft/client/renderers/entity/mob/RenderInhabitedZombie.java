/*    */ package thaumcraft.client.renderers.entity.mob;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBiped;
/*    */ import net.minecraft.client.model.ModelZombieVillager;
/*    */ import net.minecraft.client.renderer.entity.RenderManager;
/*    */ import net.minecraft.client.renderer.entity.RenderZombie;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.monster.EntityZombie;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class RenderInhabitedZombie
/*    */   extends RenderZombie {
/*    */   public RenderInhabitedZombie(RenderManager p_i46127_1_) {
/* 18 */     super(p_i46127_1_);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 24 */     this.field_82431_q = 1;
/*    */   }
/*    */   
/*    */   private static final ResourceLocation t1 = new ResourceLocation("thaumcraft", "textures/entity/czombie.png");
/*    */   
/* 29 */   protected ResourceLocation func_110775_a(EntityZombie entity) { return t1; }
/*    */   
/*    */   private ModelBiped field_82434_o;
/*    */   private ModelZombieVillager field_82432_p;
/*    */   private int field_82431_q;
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\entity\mob\RenderInhabitedZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */