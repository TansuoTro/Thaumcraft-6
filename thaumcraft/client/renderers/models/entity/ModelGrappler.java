/*    */ package thaumcraft.client.renderers.models.entity;
/*    */ 
/*    */ import net.minecraft.client.model.ModelBase;
/*    */ import net.minecraft.client.model.ModelRenderer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModelGrappler
/*    */   extends ModelBase
/*    */ {
/*    */   ModelRenderer core;
/*    */   ModelRenderer prong1;
/*    */   ModelRenderer prong2;
/*    */   ModelRenderer prong3;
/*    */   
/*    */   public ModelGrappler() {
/* 17 */     this.field_78090_t = 64;
/* 18 */     this.field_78089_u = 32;
/*    */     
/* 20 */     this.core = new ModelRenderer(this, 0, 0);
/* 21 */     this.core.func_78789_a(-1.5F, -1.5F, -1.5F, 3, 3, 3);
/* 22 */     this.core.func_78793_a(0.0F, 0.0F, 0.0F);
/* 23 */     this.core.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 24 */     setRotation(this.core, 0.0F, 0.0F, 0.0F);
/*    */     
/* 26 */     this.prong1 = new ModelRenderer(this, 0, 10);
/* 27 */     this.prong1.func_78789_a(-0.5F, -0.5F, -2.5F, 1, 1, 5);
/* 28 */     this.prong1.func_78793_a(0.0F, 0.0F, 0.0F);
/* 29 */     this.prong1.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 30 */     setRotation(this.prong1, 0.0F, 0.0F, 0.0F);
/*    */     
/* 32 */     this.prong2 = new ModelRenderer(this, 0, 10);
/* 33 */     this.prong2.func_78789_a(-0.5F, -0.5F, -2.5F, 1, 1, 5);
/* 34 */     this.prong2.func_78793_a(0.0F, 0.0F, 0.0F);
/* 35 */     this.prong2.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 36 */     setRotation(this.prong2, 0.0F, 1.5707964F, 0.0F);
/*    */     
/* 38 */     this.prong3 = new ModelRenderer(this, 0, 10);
/* 39 */     this.prong3.func_78789_a(-0.5F, -0.5F, -2.5F, 1, 1, 5);
/* 40 */     this.prong3.func_78793_a(0.0F, 0.0F, 0.0F);
/* 41 */     this.prong3.func_78787_b(this.field_78090_t, this.field_78089_u);
/* 42 */     setRotation(this.prong3, 1.5707964F, 1.5707964F, 0.0F);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render() {
/* 48 */     this.core.func_78785_a(0.0625F);
/* 49 */     this.prong1.func_78785_a(0.0625F);
/* 50 */     this.prong2.func_78785_a(0.0625F);
/* 51 */     this.prong3.func_78785_a(0.0625F);
/*    */   }
/*    */ 
/*    */   
/*    */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 56 */     model.field_78795_f = x;
/* 57 */     model.field_78796_g = y;
/* 58 */     model.field_78808_h = z;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelGrappler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */