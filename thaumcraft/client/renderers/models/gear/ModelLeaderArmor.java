/*     */ package thaumcraft.client.renderers.models.gear;
/*     */ 
/*     */ import net.minecraft.client.model.ModelRenderer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelLeaderArmor
/*     */   extends ModelCustomArmor
/*     */ {
/*     */   ModelRenderer Helmet;
/*     */   ModelRenderer CollarF;
/*     */   ModelRenderer CollarB;
/*     */   ModelRenderer CollarR;
/*     */   ModelRenderer CollarL;
/*     */   ModelRenderer BeltR;
/*     */   ModelRenderer Mbelt;
/*     */   ModelRenderer MbeltL;
/*     */   ModelRenderer MbeltR;
/*     */   ModelRenderer BeltL;
/*     */   ModelRenderer CloakTL;
/*     */   ModelRenderer Cloak3;
/*     */   ModelRenderer CloakTR;
/*     */   ModelRenderer Cloak1;
/*     */   ModelRenderer Cloak2;
/*     */   ModelRenderer Chestplate;
/*     */   ModelRenderer ChestOrnament;
/*     */   ModelRenderer ChestClothL;
/*     */   ModelRenderer ChestClothR;
/*     */   ModelRenderer Backplate;
/*     */   ModelRenderer GauntletstrapR1;
/*     */   ModelRenderer GauntletstrapR2;
/*     */   ModelRenderer ShoulderR;
/*     */   ModelRenderer ShoulderR1;
/*     */   ModelRenderer ShoulderR2;
/*     */   ModelRenderer ShoulderR5;
/*     */   ModelRenderer ShoulderR3;
/*     */   ModelRenderer ShoulderR4;
/*     */   ModelRenderer GauntletR2;
/*     */   ModelRenderer GauntletR;
/*     */   ModelRenderer GauntletL2;
/*     */   ModelRenderer GauntletstrapL1;
/*     */   ModelRenderer GauntletstrapL2;
/*     */   ModelRenderer ShoulderL;
/*     */   ModelRenderer ShoulderL1;
/*     */   ModelRenderer ShoulderL2;
/*     */   ModelRenderer ShoulderL3;
/*     */   ModelRenderer ShoulderL5;
/*     */   ModelRenderer ShoulderL4;
/*     */   ModelRenderer GauntletL;
/*     */   ModelRenderer LegClothR;
/*     */   ModelRenderer BackpanelR2;
/*     */   ModelRenderer BackpanelR3;
/*     */   ModelRenderer BackpanelR4;
/*     */   ModelRenderer LegClothL;
/*     */   ModelRenderer BackpanelL4;
/*     */   ModelRenderer BackpanelL2;
/*     */   ModelRenderer BackpanelL3;
/*     */   ModelRenderer BackpanelL1;
/*     */   ModelRenderer BackpanelR1;
/*     */   
/*     */   public ModelLeaderArmor(float f) {
/*  71 */     super(f, 0, 128, 64);
/*  72 */     this.field_78090_t = 128;
/*  73 */     this.field_78089_u = 64;
/*     */     
/*  75 */     this.Helmet = new ModelRenderer(this, 41, 8);
/*  76 */     this.Helmet.func_78789_a(-4.5F, -9.0F, -4.5F, 9, 9, 9);
/*  77 */     this.Helmet.func_78787_b(128, 64);
/*  78 */     setRotation(this.Helmet, 0.0F, 0.0F, 0.0F);
/*     */     
/*  80 */     this.CollarF = new ModelRenderer(this, 17, 31);
/*  81 */     this.CollarF.func_78789_a(-4.5F, -1.5F, -3.0F, 9, 4, 1);
/*  82 */     this.CollarF.func_78793_a(0.0F, 0.0F, -2.5F);
/*  83 */     setRotation(this.CollarF, 0.2268928F, 0.0F, 0.0F);
/*  84 */     this.CollarB = new ModelRenderer(this, 17, 26);
/*  85 */     this.CollarB.func_78789_a(-4.5F, -1.5F, 7.0F, 9, 4, 1);
/*  86 */     this.CollarB.func_78793_a(0.0F, 0.0F, -2.5F);
/*  87 */     setRotation(this.CollarB, 0.2268928F, 0.0F, 0.0F);
/*  88 */     this.CollarR = new ModelRenderer(this, 17, 11);
/*  89 */     this.CollarR.func_78789_a(-5.5F, -1.5F, -3.0F, 1, 4, 11);
/*  90 */     this.CollarR.func_78793_a(0.0F, 0.0F, -2.5F);
/*  91 */     setRotation(this.CollarR, 0.2268928F, 0.0F, 0.0F);
/*  92 */     this.CollarL = new ModelRenderer(this, 17, 11);
/*  93 */     this.CollarL.func_78789_a(4.5F, -1.5F, -3.0F, 1, 4, 11);
/*  94 */     this.CollarL.func_78793_a(0.0F, 0.0F, -2.5F);
/*  95 */     setRotation(this.CollarL, 0.2268928F, 0.0F, 0.0F);
/*     */     
/*  97 */     this.BeltR = new ModelRenderer(this, 76, 44);
/*  98 */     this.BeltR.func_78789_a(-5.0F, 4.0F, -3.0F, 1, 3, 6);
/*  99 */     this.Mbelt = new ModelRenderer(this, 56, 55);
/* 100 */     this.Mbelt.func_78789_a(-4.0F, 8.0F, -3.0F, 8, 4, 1);
/* 101 */     this.MbeltL = new ModelRenderer(this, 76, 44);
/* 102 */     this.MbeltL.func_78789_a(4.0F, 8.0F, -3.0F, 1, 3, 6);
/* 103 */     this.MbeltR = new ModelRenderer(this, 76, 44);
/* 104 */     this.MbeltR.func_78789_a(-5.0F, 8.0F, -3.0F, 1, 3, 6);
/* 105 */     this.BeltL = new ModelRenderer(this, 76, 44);
/* 106 */     this.BeltL.func_78789_a(4.0F, 4.0F, -3.0F, 1, 3, 6);
/*     */     
/* 108 */     this.CloakTL = new ModelRenderer(this, 0, 43);
/* 109 */     this.CloakTL.func_78789_a(2.5F, 1.0F, -1.0F, 2, 1, 3);
/* 110 */     this.CloakTL.func_78793_a(0.0F, 0.0F, 3.0F);
/* 111 */     setRotation(this.CloakTL, 0.1396263F, 0.0F, 0.0F);
/* 112 */     this.Cloak3 = new ModelRenderer(this, 0, 59);
/* 113 */     this.Cloak3.func_78789_a(-4.5F, 17.0F, -3.7F, 9, 4, 1);
/* 114 */     this.Cloak3.func_78793_a(0.0F, 0.0F, 3.0F);
/* 115 */     setRotation(this.Cloak3, 0.4465716F, 0.0F, 0.0F);
/* 116 */     this.CloakTR = new ModelRenderer(this, 0, 43);
/* 117 */     this.CloakTR.func_78789_a(-4.5F, 1.0F, -1.0F, 2, 1, 3);
/* 118 */     this.CloakTR.func_78793_a(0.0F, 0.0F, 3.0F);
/* 119 */     setRotation(this.CloakTR, 0.1396263F, 0.0F, 0.0F);
/* 120 */     this.Cloak1 = new ModelRenderer(this, 0, 47);
/* 121 */     this.Cloak1.func_78789_a(-4.5F, 2.0F, 1.0F, 9, 12, 1);
/* 122 */     this.Cloak1.func_78793_a(0.0F, 0.0F, 3.0F);
/* 123 */     setRotation(this.Cloak1, 0.1396263F, 0.0F, 0.0F);
/* 124 */     this.Cloak2 = new ModelRenderer(this, 0, 59);
/* 125 */     this.Cloak2.func_78789_a(-4.5F, 14.0F, -1.3F, 9, 4, 1);
/* 126 */     this.Cloak2.func_78793_a(0.0F, 0.0F, 3.0F);
/* 127 */     setRotation(this.Cloak2, 0.3069452F, 0.0F, 0.0F);
/*     */     
/* 129 */     this.Chestplate = new ModelRenderer(this, 56, 45);
/* 130 */     this.Chestplate.func_78789_a(-4.0F, 1.0F, -3.8F, 8, 7, 2);
/* 131 */     this.ChestOrnament = new ModelRenderer(this, 76, 53);
/* 132 */     this.ChestOrnament.func_78789_a(-2.5F, 3.0F, -4.8F, 5, 5, 1);
/* 133 */     this.ChestClothL = new ModelRenderer(this, 20, 47);
/* 134 */     this.ChestClothL.field_78809_i = true;
/* 135 */     this.ChestClothL.func_78789_a(1.5F, 1.2F, -4.5F, 3, 9, 1);
/* 136 */     setRotation(this.ChestClothL, 0.0663225F, 0.0F, 0.0F);
/* 137 */     this.ChestClothR = new ModelRenderer(this, 20, 47);
/* 138 */     this.ChestClothR.func_78789_a(-4.5F, 1.2F, -4.5F, 3, 9, 1);
/* 139 */     setRotation(this.ChestClothR, 0.0663225F, 0.0F, 0.0F);
/* 140 */     this.Backplate = new ModelRenderer(this, 36, 45);
/* 141 */     this.Backplate.func_78789_a(-4.0F, 1.0F, 2.0F, 8, 11, 2);
/*     */     
/* 143 */     this.GauntletR = new ModelRenderer(this, 100, 26);
/* 144 */     this.GauntletR.func_78789_a(-3.5F, 3.5F, -2.5F, 2, 6, 5);
/* 145 */     this.GauntletL = new ModelRenderer(this, 114, 26);
/* 146 */     this.GauntletL.func_78789_a(1.5F, 3.5F, -2.5F, 2, 6, 5);
/* 147 */     this.GauntletstrapL1 = new ModelRenderer(this, 84, 31);
/* 148 */     this.GauntletstrapL1.field_78809_i = true;
/* 149 */     this.GauntletstrapL1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 150 */     this.GauntletstrapL2 = new ModelRenderer(this, 84, 31);
/* 151 */     this.GauntletstrapL2.field_78809_i = true;
/* 152 */     this.GauntletstrapL2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 153 */     this.GauntletstrapR1 = new ModelRenderer(this, 84, 31);
/* 154 */     this.GauntletstrapR1.func_78789_a(-1.5F, 3.5F, -2.5F, 3, 1, 5);
/* 155 */     this.GauntletstrapR2 = new ModelRenderer(this, 84, 31);
/* 156 */     this.GauntletstrapR2.func_78789_a(-1.5F, 6.5F, -2.5F, 3, 1, 5);
/* 157 */     this.GauntletR2 = new ModelRenderer(this, 102, 37);
/* 158 */     this.GauntletR2.func_78789_a(-5.0F, 3.5F, -2.0F, 1, 5, 4);
/* 159 */     setRotation(this.GauntletR2, 0.0F, 0.0F, -0.1675516F);
/* 160 */     this.GauntletL2 = new ModelRenderer(this, 102, 37);
/* 161 */     this.GauntletL2.func_78789_a(4.0F, 3.5F, -2.0F, 1, 5, 4);
/* 162 */     setRotation(this.GauntletL2, 0.0F, 0.0F, 0.1675516F);
/*     */     
/* 164 */     this.ShoulderR = new ModelRenderer(this, 56, 35);
/* 165 */     this.ShoulderR.func_78789_a(-3.5F, -2.5F, -2.5F, 5, 5, 5);
/* 166 */     this.ShoulderR1 = new ModelRenderer(this, 0, 0);
/* 167 */     this.ShoulderR1.func_78789_a(-4.3F, -1.5F, -3.0F, 3, 5, 6);
/* 168 */     setRotation(this.ShoulderR1, 0.0F, 0.0F, 0.7853982F);
/* 169 */     this.ShoulderR2 = new ModelRenderer(this, 0, 19);
/* 170 */     this.ShoulderR2.func_78789_a(-3.3F, 3.5F, -2.5F, 1, 1, 5);
/* 171 */     setRotation(this.ShoulderR2, 0.0F, 0.0F, 0.7853982F);
/* 172 */     this.ShoulderR5 = new ModelRenderer(this, 18, 4);
/* 173 */     this.ShoulderR5.func_78789_a(-2.3F, -1.5F, 3.0F, 1, 6, 1);
/* 174 */     setRotation(this.ShoulderR5, 0.0F, 0.0F, 0.7853982F);
/* 175 */     this.ShoulderR3 = new ModelRenderer(this, 0, 11);
/* 176 */     this.ShoulderR3.func_78789_a(-2.3F, 3.5F, -3.0F, 1, 2, 6);
/* 177 */     setRotation(this.ShoulderR3, 0.0F, 0.0F, 0.7853982F);
/* 178 */     this.ShoulderR4 = new ModelRenderer(this, 18, 4);
/* 179 */     this.ShoulderR4.func_78789_a(-2.3F, -1.5F, -4.0F, 1, 6, 1);
/* 180 */     setRotation(this.ShoulderR4, 0.0F, 0.0F, 0.7853982F);
/*     */     
/* 182 */     this.ShoulderL = new ModelRenderer(this, 56, 35);
/* 183 */     this.ShoulderL.func_78789_a(-1.5F, -2.5F, -2.5F, 5, 5, 5);
/* 184 */     this.ShoulderL1 = new ModelRenderer(this, 0, 0);
/* 185 */     this.ShoulderL1.func_78789_a(1.3F, -1.5F, -3.0F, 3, 5, 6);
/* 186 */     setRotation(this.ShoulderL1, 0.0F, 0.0F, -0.7853982F);
/* 187 */     this.ShoulderL2 = new ModelRenderer(this, 0, 19);
/* 188 */     this.ShoulderL2.field_78809_i = true;
/* 189 */     this.ShoulderL2.func_78789_a(2.3F, 3.5F, -2.5F, 1, 1, 5);
/* 190 */     setRotation(this.ShoulderL2, 0.0F, 0.0F, -0.7853982F);
/* 191 */     this.ShoulderL3 = new ModelRenderer(this, 0, 11);
/* 192 */     this.ShoulderL3.func_78789_a(1.3F, 3.5F, -3.0F, 1, 2, 6);
/* 193 */     setRotation(this.ShoulderL3, 0.0F, 0.0F, -0.7853982F);
/* 194 */     this.ShoulderL5 = new ModelRenderer(this, 18, 4);
/* 195 */     this.ShoulderL5.func_78789_a(1.3F, -1.5F, 3.0F, 1, 6, 1);
/* 196 */     this.ShoulderL5.func_78787_b(128, 64);
/* 197 */     setRotation(this.ShoulderL5, 0.0F, 0.0F, -0.7853982F);
/* 198 */     this.ShoulderL4 = new ModelRenderer(this, 18, 4);
/* 199 */     this.ShoulderL4.func_78789_a(1.3F, -1.5F, -4.0F, 1, 6, 1);
/* 200 */     setRotation(this.ShoulderL4, 0.0F, 0.0F, -0.7853982F);
/*     */     
/* 202 */     this.LegClothR = new ModelRenderer(this, 20, 55);
/* 203 */     this.LegClothR.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 204 */     this.LegClothR.func_78793_a(-4.5F, 10.4F, -3.9F);
/* 205 */     setRotation(this.LegClothR, -0.0349066F, 0.0F, 0.0F);
/* 206 */     this.LegClothL = new ModelRenderer(this, 20, 55);
/* 207 */     this.LegClothL.field_78809_i = true;
/* 208 */     this.LegClothL.func_78789_a(0.0F, 0.0F, 0.0F, 3, 8, 1);
/* 209 */     this.LegClothL.func_78793_a(1.5F, 10.4F, -3.9F);
/* 210 */     setRotation(this.LegClothL, -0.0349066F, 0.0F, 0.0F);
/*     */     
/* 212 */     this.BackpanelR1 = new ModelRenderer(this, 0, 25);
/* 213 */     this.BackpanelR1.func_78789_a(-3.0F, -0.5F, 2.5F, 5, 7, 1);
/* 214 */     setRotation(this.BackpanelR1, 0.0698132F, 0.0F, 0.0F);
/* 215 */     this.BackpanelR2 = new ModelRenderer(this, 96, 14);
/* 216 */     this.BackpanelR2.func_78789_a(-3.0F, -0.5F, -2.5F, 5, 3, 5);
/* 217 */     setRotation(this.BackpanelR2, 0.0F, 0.0F, 0.1396263F);
/* 218 */     this.BackpanelR3 = new ModelRenderer(this, 116, 13);
/* 219 */     this.BackpanelR3.func_78789_a(-3.0F, 2.5F, -2.5F, 1, 4, 5);
/* 220 */     setRotation(this.BackpanelR3, 0.0F, 0.0F, 0.1396263F);
/* 221 */     this.BackpanelR4 = new ModelRenderer(this, 0, 25);
/* 222 */     this.BackpanelR4.field_78809_i = true;
/* 223 */     this.BackpanelR4.func_78789_a(-3.0F, -0.5F, -3.5F, 5, 7, 1);
/* 224 */     setRotation(this.BackpanelR4, -0.0349066F, 0.0F, 0.0F);
/*     */     
/* 226 */     this.BackpanelL1 = new ModelRenderer(this, 0, 25);
/* 227 */     this.BackpanelL1.func_78789_a(-2.0F, -0.5F, 2.5F, 5, 7, 1);
/* 228 */     setRotation(this.BackpanelL1, 0.0698132F, 0.0F, 0.0F);
/* 229 */     this.BackpanelL4 = new ModelRenderer(this, 0, 25);
/* 230 */     this.BackpanelL4.func_78789_a(-2.0F, -0.5F, -3.5F, 5, 7, 1);
/* 231 */     setRotation(this.BackpanelL4, -0.0349066F, 0.0F, 0.0F);
/* 232 */     this.BackpanelL2 = new ModelRenderer(this, 96, 14);
/* 233 */     this.BackpanelL2.func_78789_a(-2.0F, -0.5F, -2.5F, 5, 3, 5);
/* 234 */     setRotation(this.BackpanelL2, 0.0F, 0.0F, -0.1396263F);
/* 235 */     this.BackpanelL3 = new ModelRenderer(this, 116, 13);
/* 236 */     this.BackpanelL3.func_78789_a(2.0F, 2.5F, -2.5F, 1, 4, 5);
/* 237 */     setRotation(this.BackpanelL3, 0.0F, 0.0F, -0.1396263F);
/*     */     
/* 239 */     this.field_178720_f.field_78804_l.clear();
/* 240 */     this.field_78116_c.field_78804_l.clear();
/* 241 */     this.field_78116_c.func_78792_a(this.Helmet);
/*     */     
/* 243 */     this.field_78115_e.field_78804_l.clear();
/* 244 */     this.field_178721_j.field_78804_l.clear();
/* 245 */     this.field_178722_k.field_78804_l.clear();
/*     */     
/* 247 */     this.field_78115_e.func_78792_a(this.Mbelt);
/* 248 */     this.field_78115_e.func_78792_a(this.MbeltL);
/* 249 */     this.field_78115_e.func_78792_a(this.MbeltR);
/*     */     
/* 251 */     if (f >= 1.0F) {
/*     */ 
/*     */       
/* 254 */       this.field_78115_e.func_78792_a(this.BeltL);
/* 255 */       this.field_78115_e.func_78792_a(this.BeltR);
/* 256 */       this.field_78115_e.func_78792_a(this.Chestplate);
/* 257 */       this.field_78115_e.func_78792_a(this.ChestOrnament);
/* 258 */       this.field_78115_e.func_78792_a(this.ChestClothR);
/* 259 */       this.field_78115_e.func_78792_a(this.ChestClothL);
/* 260 */       this.field_78115_e.func_78792_a(this.LegClothR);
/* 261 */       this.field_78115_e.func_78792_a(this.LegClothL);
/* 262 */       this.field_78115_e.func_78792_a(this.Backplate);
/* 263 */       this.field_78115_e.func_78792_a(this.CollarB);
/* 264 */       this.field_78115_e.func_78792_a(this.CollarR);
/* 265 */       this.field_78115_e.func_78792_a(this.CollarL);
/* 266 */       this.field_78115_e.func_78792_a(this.CollarF);
/* 267 */       this.field_78115_e.func_78792_a(this.Cloak1);
/* 268 */       this.field_78115_e.func_78792_a(this.Cloak2);
/* 269 */       this.field_78115_e.func_78792_a(this.Cloak3);
/* 270 */       this.field_78115_e.func_78792_a(this.CloakTL);
/* 271 */       this.field_78115_e.func_78792_a(this.CloakTR);
/*     */     } 
/*     */     
/* 274 */     this.field_178723_h.field_78804_l.clear();
/* 275 */     this.field_178723_h.func_78792_a(this.ShoulderR);
/* 276 */     this.field_178723_h.func_78792_a(this.ShoulderR1);
/* 277 */     this.field_178723_h.func_78792_a(this.ShoulderR2);
/* 278 */     this.field_178723_h.func_78792_a(this.ShoulderR3);
/* 279 */     this.field_178723_h.func_78792_a(this.ShoulderR4);
/* 280 */     this.field_178723_h.func_78792_a(this.ShoulderR5);
/* 281 */     this.field_178723_h.func_78792_a(this.GauntletR);
/* 282 */     this.field_178723_h.func_78792_a(this.GauntletR2);
/* 283 */     this.field_178723_h.func_78792_a(this.GauntletstrapR1);
/* 284 */     this.field_178723_h.func_78792_a(this.GauntletstrapR2);
/*     */     
/* 286 */     this.field_178724_i.field_78804_l.clear();
/* 287 */     this.field_178724_i.func_78792_a(this.ShoulderL);
/* 288 */     this.field_178724_i.func_78792_a(this.ShoulderL1);
/* 289 */     this.field_178724_i.func_78792_a(this.ShoulderL2);
/* 290 */     this.field_178724_i.func_78792_a(this.ShoulderL3);
/* 291 */     this.field_178724_i.func_78792_a(this.ShoulderL4);
/* 292 */     this.field_178724_i.func_78792_a(this.ShoulderL5);
/* 293 */     this.field_178724_i.func_78792_a(this.GauntletL);
/* 294 */     this.field_178724_i.func_78792_a(this.GauntletL2);
/* 295 */     this.field_178724_i.func_78792_a(this.GauntletstrapL1);
/* 296 */     this.field_178724_i.func_78792_a(this.GauntletstrapL2);
/*     */     
/* 298 */     this.field_178721_j.func_78792_a(this.BackpanelR1);
/* 299 */     this.field_178721_j.func_78792_a(this.BackpanelR2);
/* 300 */     this.field_178721_j.func_78792_a(this.BackpanelR3);
/* 301 */     this.field_178721_j.func_78792_a(this.BackpanelR4);
/*     */     
/* 303 */     this.field_178722_k.func_78792_a(this.BackpanelL1);
/* 304 */     this.field_178722_k.func_78792_a(this.BackpanelL2);
/* 305 */     this.field_178722_k.func_78792_a(this.BackpanelL3);
/* 306 */     this.field_178722_k.func_78792_a(this.BackpanelL4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
/* 313 */     func_78087_a(f, f1, f2, f3, f4, f5, entity);
/*     */     
/* 315 */     float a = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
/* 316 */     float b = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
/* 317 */     float c = Math.min(a, b);
/*     */     
/* 319 */     this.LegClothR.field_78795_f = a - 0.1047198F;
/* 320 */     this.LegClothL.field_78795_f = b - 0.1047198F;
/*     */     
/* 322 */     this.Cloak1.field_78795_f = -c / 2.0F + 0.1396263F;
/* 323 */     this.Cloak2.field_78795_f = -c / 2.0F + 0.3069452F;
/* 324 */     this.Cloak3.field_78795_f = -c / 2.0F + 0.4465716F;
/*     */     
/* 326 */     if (this.field_78091_s) {
/* 327 */       float f6 = 2.0F;
/* 328 */       GL11.glPushMatrix();
/* 329 */       GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
/* 330 */       GL11.glTranslatef(0.0F, 16.0F * f5, 0.0F);
/* 331 */       this.field_78116_c.func_78785_a(f5);
/* 332 */       GL11.glPopMatrix();
/* 333 */       GL11.glPushMatrix();
/* 334 */       GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
/* 335 */       GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);
/* 336 */       this.field_78115_e.func_78785_a(f5);
/* 337 */       this.field_178723_h.func_78785_a(f5);
/* 338 */       this.field_178724_i.func_78785_a(f5);
/* 339 */       this.field_178721_j.func_78785_a(f5);
/* 340 */       this.field_178722_k.func_78785_a(f5);
/* 341 */       this.field_178720_f.func_78785_a(f5);
/* 342 */       GL11.glPopMatrix();
/*     */     } else {
/* 344 */       GL11.glPushMatrix();
/* 345 */       GL11.glScalef(1.01F, 1.01F, 1.01F);
/* 346 */       this.field_78116_c.func_78785_a(f5);
/* 347 */       GL11.glPopMatrix();
/* 348 */       this.field_78115_e.func_78785_a(f5);
/* 349 */       this.field_178723_h.func_78785_a(f5);
/* 350 */       this.field_178724_i.func_78785_a(f5);
/* 351 */       this.field_178721_j.func_78785_a(f5);
/* 352 */       this.field_178722_k.func_78785_a(f5);
/* 353 */       this.field_178720_f.func_78785_a(f5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setRotation(ModelRenderer model, float x, float y, float z) {
/* 358 */     model.field_78795_f = x;
/* 359 */     model.field_78796_g = y;
/* 360 */     model.field_78808_h = z;
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\renderers\models\gear\ModelLeaderArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */