/*    */ package thaumcraft.client.lib.obj;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ObjModelLoader
/*    */   implements IModelCustomLoader
/*    */ {
/* 12 */   public String getType() { return "OBJ model"; }
/*    */ 
/*    */   
/* 15 */   private static final String[] types = { "obj" };
/*    */ 
/*    */ 
/*    */   
/* 19 */   public String[] getSuffixes() { return types; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public IModelCustom loadInstance(ResourceLocation resource) throws WavefrontObject.ModelFormatException { return new WavefrontObject(resource); }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\client\lib\obj\ObjModelLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */