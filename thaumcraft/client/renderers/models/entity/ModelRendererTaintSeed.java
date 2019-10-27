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
/*     */ public class ModelRendererTaintSeed
/*     */   extends ModelRenderer
/*     */ {
/*     */   private int textureOffsetX;
/*     */   private int textureOffsetY;
/*     */   
/*  18 */   public ModelRendererTaintSeed(ModelBase par1ModelBase) { super(par1ModelBase); }
/*     */   private boolean compiled; private int displayList;
/*     */   private ModelBase baseModel;
/*     */   
/*     */   public ModelRendererTaintSeed(ModelBase par1ModelBase, int par2, int par3) {
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
/*  42 */   static int q = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void render(float par1, float tt, float height) {
/*  48 */     float qq = (float)(Math.PI * (q / 7.0F));
/*  49 */     float scale = height - (float)Math.sin(qq);
/*  50 */     float pulse = (float)Math.sin((tt / 12.0F - qq)) * 0.33F;
/*  51 */     pulse *= pulse;
/*     */     
/*  53 */     q++;
/*  54 */     if (q > 7) q = 0;
/*     */     
/*  56 */     if (!this.field_78807_k)
/*     */     {
/*  58 */       if (this.field_78806_j) {
/*     */         
/*  60 */         if (!this.compiled)
/*     */         {
/*  62 */           compileDisplayList(par1);
/*     */         }
/*     */         
/*  65 */         GL11.glTranslatef(this.field_82906_o, this.field_82908_p, this.field_82907_q);
/*     */ 
/*     */         
/*  68 */         if (this.field_78795_f == 0.0F && this.field_78796_g == 0.0F && this.field_78808_h == 0.0F) {
/*     */           
/*  70 */           if (this.field_78800_c == 0.0F && this.field_78797_d == 0.0F && this.field_78798_e == 0.0F) {
/*     */             
/*  72 */             GL11.glCallList(this.displayList);
/*     */             
/*  74 */             if (this.field_78805_m != null)
/*     */             {
/*  76 */               for (int i = 0; i < this.field_78805_m.size(); i++)
/*     */               {
/*  78 */                 GL11.glPushMatrix();
/*  79 */                 GL11.glScalef(scale + pulse, scale * 0.9F, scale + pulse);
/*  80 */                 ((ModelRendererTaintSeed)this.field_78805_m.get(i)).render(par1, tt, height);
/*  81 */                 GL11.glPopMatrix();
/*     */               }
/*     */             
/*     */             }
/*     */           } else {
/*     */             
/*  87 */             GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*  88 */             GL11.glCallList(this.displayList);
/*     */             
/*  90 */             if (this.field_78805_m != null)
/*     */             {
/*  92 */               for (int i = 0; i < this.field_78805_m.size(); i++) {
/*     */                 
/*  94 */                 GL11.glPushMatrix();
/*  95 */                 GL11.glScalef(scale + pulse, scale * 0.9F, scale + pulse);
/*  96 */                 ((ModelRendererTaintSeed)this.field_78805_m.get(i)).render(par1, tt, height);
/*  97 */                 GL11.glPopMatrix();
/*     */               } 
/*     */             }
/*     */             
/* 101 */             GL11.glTranslatef(-this.field_78800_c * par1, -this.field_78797_d * par1, -this.field_78798_e * par1);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 106 */           GL11.glPushMatrix();
/* 107 */           GL11.glTranslatef(this.field_78800_c * par1, this.field_78797_d * par1, this.field_78798_e * par1);
/*     */           
/* 109 */           if (this.field_78808_h != 0.0F)
/*     */           {
/* 111 */             GL11.glRotatef(this.field_78808_h * 57.295776F, 0.0F, 0.0F, 1.0F);
/*     */           }
/*     */           
/* 114 */           if (this.field_78796_g != 0.0F)
/*     */           {
/* 116 */             GL11.glRotatef(this.field_78796_g * 57.295776F, 0.0F, 1.0F, 0.0F);
/*     */           }
/*     */           
/* 119 */           if (this.field_78795_f != 0.0F)
/*     */           {
/* 121 */             GL11.glRotatef(this.field_78795_f * 57.295776F, 1.0F, 0.0F, 0.0F);
/*     */           }
/*     */           
/* 124 */           GL11.glCallList(this.displayList);
/*     */           
/* 126 */           if (this.field_78805_m != null)
/*     */           {
/* 128 */             for (int i = 0; i < this.field_78805_m.size(); i++) {
/*     */               
/* 130 */               GL11.glPushMatrix();
/* 131 */               GL11.glScalef(scale + pulse, scale * 0.9F, scale + pulse);
/* 132 */               ((ModelRendererTaintSeed)this.field_78805_m.get(i)).render(par1, tt, height);
/* 133 */               GL11.glPopMatrix();
/*     */             } 
/*     */           }
/*     */           
/* 137 */           GL11.glPopMatrix();
/*     */         } 
/*     */         
/* 140 */         GL11.glTranslatef(-this.field_82906_o, -this.field_82908_p, -this.field_82907_q);
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
/* 153 */     this.displayList = GLAllocation.func_74526_a(1);
/* 154 */     GL11.glNewList(this.displayList, 4864);
/* 155 */     Tessellator tessellator = Tessellator.func_178181_a();
/*     */     
/* 157 */     for (int i = 0; i < this.field_78804_l.size(); i++)
/*     */     {
/* 159 */       ((ModelBox)this.field_78804_l.get(i)).func_178780_a(tessellator.func_178180_c(), par1);
/*     */     }
/*     */     
/* 162 */     GL11.glEndList();
/* 163 */     this.compiled = true;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\entity\ModelRendererTaintSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */