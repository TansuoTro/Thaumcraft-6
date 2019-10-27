/*    */ package thaumcraft.api.internal;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import thaumcraft.api.ThaumcraftApi;
/*    */ import thaumcraft.api.aspects.AspectList;
/*    */ import thaumcraft.api.crafting.IThaumcraftRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommonInternals
/*    */ {
/* 22 */   public static HashMap<String, ResourceLocation> jsonLocs = new HashMap();
/* 23 */   public static ArrayList<ThaumcraftApi.EntityTags> scanEntities = new ArrayList();
/* 24 */   public static HashMap<ResourceLocation, IThaumcraftRecipe> craftingRecipeCatalog = new HashMap();
/* 25 */   public static HashMap<ResourceLocation, Object> craftingRecipeCatalogFake = new HashMap();
/* 26 */   public static ArrayList<ThaumcraftApi.SmeltBonus> smeltingBonus = new ArrayList();
/* 27 */   public static ConcurrentHashMap<Integer, AspectList> objectTags = new ConcurrentHashMap();
/* 28 */   public static HashMap<Object, Integer> warpMap = new HashMap();
/* 29 */   public static HashMap<String, ItemStack> seedList = new HashMap();
/*    */ 
/*    */   
/* 32 */   public static IThaumcraftRecipe getCatalogRecipe(ResourceLocation key) { return (IThaumcraftRecipe)craftingRecipeCatalog.get(key); }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public static Object getCatalogRecipeFake(ResourceLocation key) { return craftingRecipeCatalogFake.get(key); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int generateUniqueItemstackId(ItemStack stack) {
/* 45 */     ItemStack sc = stack.func_77946_l();
/* 46 */     sc.func_190920_e(1);
/* 47 */     String ss = sc.serializeNBT().toString();
/* 48 */     return ss.hashCode();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int generateUniqueItemstackIdStripped(ItemStack stack) {
/* 58 */     ItemStack sc = stack.func_77946_l();
/* 59 */     sc.func_190920_e(1);
/* 60 */     sc.func_77982_d(null);
/* 61 */     String ss = sc.serializeNBT().toString();
/* 62 */     return ss.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\internal\CommonInternals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */