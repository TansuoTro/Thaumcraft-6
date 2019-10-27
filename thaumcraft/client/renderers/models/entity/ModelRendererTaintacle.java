/*     */ package thaumcraft.client.renderers.models.entity;
/*     */ 
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.model.ModelBox;
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.client.renderer.GLAllocation;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraftforge.fml.relauncher.Side;
/*     */ import net.minecraftforge.fml.relauncher.SideOnly;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelRendererTaintacle
/*     */   extends ModelRenderer
/*     */ {
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   
/*  18 */   public ModelRendererTaintacle(ModelBase par1ModelBase) { super(par1ModelBase); }
/*     */   private boolean compiled; private int displayList;
/*     */   private ModelBase baseModel;
/*     */   
/*     */   public ModelRendererTaintacle(ModelBase par1ModelBase, int par2, int par3) {
/*  23 */     this(par1ModelBase);
/*  24 */     func_78784_a(par2, par3);
/*     */   }
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void render(float par1, float scale) {
/*  46 */     if (!this.field_78807_k)
/*     */     {
/*  48 */       if (this.field_78806_j) {
/*     */         
/*  50 */         if (!this.compiled)
/*     */         {
/*  52 */           compileDisplayList(par1);
/*     */         }
/*     */         
/*  55 */         GL11.glTranslatef(this.field_82906_o, this.field_82908_p, this.field_82907_q);
/*     */ 
/*     */         
/*  58 */         if (this.field_78795_f == 0.0F && this.field_78796_g == 0.0F && this.field_78808_h == 0.0F) {
/*     */           
/*  60 */           if (this.field_78800_c == 0.0F && this.field_78797_d == 0.0F && this.field_78798_e == 0.0F) {
/*     */             
/*  62 */             GL11.glCallList(this.displayList);
/*     */             
/*  64 */             if (this.field_78805_m != null)
/*     */             {
/*  66 */               for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */               {
/*  68 */                 GL11.glPushMatrix();
/*  69 */                 GL11.glScalef(scale, scale, scale);
/*  70 */                 ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/*  71 */                 GL11.glPopMatrix();
/*     */               }
/*     */             
/*     */             }
/*     */           } else {
/*     */             
/*  77 */             GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*  78 */             GL11.glCallList(this.displayList);
/*     */             
/*  80 */             if (this.field_78805_m != null)
/*     */             {
/*  82 */               for (int i = 0; i < this.field_78805_m.size(); i++) {
/*     */                 
/*  84 */                 GL11.glPushMatrix();
/*  85 */                 GL11.glScalef(scale, scale, scale);
/*  86 */                 ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/*  87 */                 GL11.glPopMatrix();
/*     */               } 
/*     */             }
/*     */             
/*  91 */             GL11.glTranslatef(-this.field_78800_c * par1, -this.field_78797_d * par1, -this.field_78798_e * par1);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  96 */           GL11.glPushMatrix();
/*  97 */           GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*     */           
/*  99 */           if (this.field_78808_h != 0.0F)
/*     */           {
/* 101 */             GL11.glRotatef(this.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */           }
/*     */           
/* 104 */           if (this.field_78796_g != 0.0F)
/*     */           {
/* 106 */             GL11.glRotatef(this.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 109 */           if (this.field_78795_f != 0.0F)
/*     */           {
/* 111 */             GL11.glRotatef(this.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */           }
/*     */           
/* 114 */           GL11.glCallList(this.displayList);
/*     */           
/* 116 */           if (this.field_78805_m != null)
/*     */           {
/* 118 */             for (int i = 0; i < this.field_78805_m.size(); i++) {
/*     */               
/* 120 */               GL11.glPushMatrix();
/* 121 */               GL11.glScalef(scale, scale, scale);
/* 122 */               ((ModelRendererTaintacle)this.field_78805_m.get(i)).render(par1, scale);
/* 123 */               GL11.glPopMatrix();
/*     */             } 
/*     */           }
/*     */           
/* 127 */           GL11.glPopMatrix();
/*     */         } 
/*     */         
/* 130 */         GL11.glTranslatef(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private void compileDisplayList(float par1) {
/* 143 */     this.displayList = GLAllocation.func_74526_a(1);
/* 144 */     GL11.glNewList(this.displayList, 4864);
/* 145 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 147 */     for (int i = 0; i < this.field_78804_l.size(); i++)
/*     */     {
/* 149 */       ((ModelBox)this.field_78804_l.get(i)).func_178780_a(tessellator.func_178180_c(), par1);
/*     */     }
/*     */     
/* 152 */     GL11.glEndList();
/* 153 */     this.compiled = true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelRendererTaintacle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */