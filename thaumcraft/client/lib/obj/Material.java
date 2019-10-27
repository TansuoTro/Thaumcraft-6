/*    */ package thaumcraft.client.lib.obj;
/*    */ 
/*    */ import javax.vecmath.Vector3f;
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
/*    */ public class Material
/*    */ {
/*    */   public String Name;
/*    */   public Vector3f AmbientColor;
/*    */   public Vector3f DiffuseColor;
/*    */   public Vector3f SpecularColor;
/*    */   public float SpecularCoefficient;
/*    */   public float Transparency;
/*    */   public int IlluminationModel;
/*    */   public String AmbientTextureMap;
/*    */   public String DiffuseTextureMap;
/*    */   public String SpecularTextureMap;
/*    */   public String SpecularHighlightTextureMap;
/*    */   public String BumpMap;
/*    */   public String DisplacementMap;
/*    */   public String StencilDecalMap;
/*    */   public String AlphaTextureMap;
/*    */   
/* 34 */   public Material(String materialName) { this.Name = materialName; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\Material.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */