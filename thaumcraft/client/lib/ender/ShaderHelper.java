/*     */ package thaumcraft.client.lib.ender;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import org.lwjgl.opengl.ARBShaderObjects;
/*     */ import thaumcraft.common.config.ModConfig;
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
/*     */ public final class ShaderHelper
/*     */ {
/*     */   private static final int VERT = 35633;
/*     */   private static final int FRAG = 35632;
/*     */   private static final String PREFIX = "/assets/thaumcraft/shader/";
/*  49 */   public static int endShader = 0;
/*  50 */   public static int sketchShader = 0;
/*     */ 
/*     */   
/*     */   public static void initShaders() {
/*  54 */     if (!useShaders())
/*  55 */       return;  endShader = createProgram("ender.vert", "ender.frag");
/*  56 */     sketchShader = createProgram("sketch.vert", "sketch.frag");
/*     */   }
/*     */   
/*     */   public static void useShader(int shader, ShaderCallback callback) {
/*  60 */     if (!useShaders()) {
/*     */       return;
/*     */     }
/*  63 */     ARBShaderObjects.glUseProgramObjectARB(shader);
/*     */     
/*  65 */     if (shader != 0) {
/*  66 */       int time = ARBShaderObjects.glGetUniformLocationARB(shader, "time");
/*  67 */       ARBShaderObjects.glUniform1iARB(time, (Minecraft.func_71410_x().func_175606_aa()).field_70173_aa);
/*     */       
/*  69 */       if (callback != null) {
/*  70 */         callback.call(shader);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*  75 */   public static void useShader(int shader) { useShader(shader, null); }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public static void releaseShader() { useShader(0); }
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static boolean useShaders() { return (!ModConfig.CONFIG_GRAPHICS.disableShaders && OpenGlHelper.field_148824_g); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int createProgram(String vert, String frag) {
/*  90 */     int vertId = 0, fragId = 0, program = 0;
/*  91 */     if (vert != null)
/*  92 */       vertId = createShader("/assets/thaumcraft/shader/" + vert, 35633); 
/*  93 */     if (frag != null) {
/*  94 */       fragId = createShader("/assets/thaumcraft/shader/" + frag, 35632);
/*     */     }
/*  96 */     program = ARBShaderObjects.glCreateProgramObjectARB();
/*  97 */     if (program == 0) {
/*  98 */       return 0;
/*     */     }
/* 100 */     if (vert != null)
/* 101 */       ARBShaderObjects.glAttachObjectARB(program, vertId); 
/* 102 */     if (frag != null) {
/* 103 */       ARBShaderObjects.glAttachObjectARB(program, fragId);
/*     */     }
/* 105 */     ARBShaderObjects.glLinkProgramARB(program);
/* 106 */     if (ARBShaderObjects.glGetObjectParameteriARB(program, 35714) == 0) {
/* 107 */       return 0;
/*     */     }
/*     */     
/* 110 */     ARBShaderObjects.glValidateProgramARB(program);
/* 111 */     if (ARBShaderObjects.glGetObjectParameteriARB(program, 35715) == 0) {
/* 112 */       return 0;
/*     */     }
/*     */     
/* 115 */     return program;
/*     */   }
/*     */   
/*     */   private static int createShader(String filename, int shaderType) {
/* 119 */     int shader = 0;
/*     */     try {
/* 121 */       shader = ARBShaderObjects.glCreateShaderObjectARB(shaderType);
/*     */       
/* 123 */       if (shader == 0) {
/* 124 */         return 0;
/*     */       }
/* 126 */       ARBShaderObjects.glShaderSourceARB(shader, readFileAsString(filename));
/* 127 */       ARBShaderObjects.glCompileShaderARB(shader);
/*     */       
/* 129 */       if (ARBShaderObjects.glGetObjectParameteriARB(shader, 35713) == 0) {
/* 130 */         throw new RuntimeException("Error creating shader: " + getLogInfo(shader));
/*     */       }
/* 132 */       return shader;
/*     */     }
/* 134 */     catch (Exception e) {
/* 135 */       ARBShaderObjects.glDeleteObjectARB(shader);
/* 136 */       e.printStackTrace();
/* 137 */       return -1;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 142 */   private static String getLogInfo(int obj) { return ARBShaderObjects.glGetInfoLogARB(obj, ARBShaderObjects.glGetObjectParameteriARB(obj, 35716)); }
/*     */ 
/*     */   
/*     */   private static String readFileAsString(String filename) throws Exception {
/* 146 */     StringBuilder source = new StringBuilder();
/* 147 */     in = ShaderHelper.class.getResourceAsStream(filename);
/* 148 */     exception = null;
/*     */ 
/*     */     
/* 151 */     if (in == null) {
/* 152 */       return "";
/*     */     }
/*     */     try {
/* 155 */       reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
/*     */       
/* 157 */       innerExc = null;
/*     */       
/*     */       try {
/* 160 */         while ((line = reader.readLine()) != null)
/* 161 */           source.append(line).append('\n'); 
/* 162 */       } catch (Exception exc) {
/* 163 */         exception = exc;
/*     */       } finally {
/*     */         try {
/* 166 */           reader.close();
/* 167 */         } catch (Exception exc) {
/* 168 */           if (innerExc == null)
/* 169 */           { innerExc = exc; }
/* 170 */           else { exc.printStackTrace(); }
/*     */         
/*     */         } 
/*     */       } 
/* 174 */       if (innerExc != null)
/* 175 */         throw innerExc; 
/* 176 */     } catch (Exception exc) {
/* 177 */       exception = exc;
/*     */     } finally {
/*     */       try {
/* 180 */         in.close();
/* 181 */       } catch (Exception exc) {
/* 182 */         if (exception == null)
/* 183 */         { exception = exc; }
/* 184 */         else { exc.printStackTrace(); }
/*     */       
/*     */       } 
/* 187 */       if (exception != null) {
/* 188 */         throw exception;
/*     */       }
/*     */     } 
/* 191 */     return source.toString();
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\ender\ShaderHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */