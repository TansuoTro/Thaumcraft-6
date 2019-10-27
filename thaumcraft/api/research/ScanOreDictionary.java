/*    */ package thaumcraft.api.research;
/*    */ 
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import net.minecraft.block.state.IBlockState;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ import thaumcraft.api.internal.CommonInternals;
/*    */ 
/*    */ public class ScanOreDictionary implements IScanThing {
/*    */   String research;
/*    */   String[] entries;
/*    */   public ConcurrentHashMap<Integer, Boolean> cache;
/*    */   
/*    */   public ScanOreDictionary(String research, String... entries) {
/* 18 */     this.cache = new ConcurrentHashMap();
/*    */ 
/*    */     
/* 21 */     this.research = research;
/* 22 */     this.entries = entries;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean checkThing(EntityPlayer player, Object obj) {
/* 27 */     ItemStack stack = null;
/* 28 */     if (obj != null) {
/* 29 */       if (obj instanceof BlockPos) {
/* 30 */         IBlockState state = player.field_70170_p.func_180495_p((BlockPos)obj);
/* 31 */         stack = state.func_177230_c().func_185473_a(player.field_70170_p, (BlockPos)obj, state);
/*    */       
/*    */       }
/* 34 */       else if (obj instanceof ItemStack) {
/* 35 */         stack = (ItemStack)obj;
/*    */       }
/* 37 */       else if (obj instanceof EntityItem && ((EntityItem)obj).func_92059_d() != null) {
/* 38 */         stack = ((EntityItem)obj).func_92059_d();
/*    */       } 
/*    */     }
/* 41 */     if (stack != null && !stack.func_190926_b()) {
/* 42 */       int hid = CommonInternals.generateUniqueItemstackId(stack);
/* 43 */       if (this.cache.containsKey(Integer.valueOf(hid))) {
/* 44 */         return ((Boolean)this.cache.get(Integer.valueOf(hid))).booleanValue();
/*    */       }
/*    */       
/* 47 */       int[] ids = OreDictionary.getOreIDs(stack);
/* 48 */       for (String entry : this.entries) {
/* 49 */         for (int id : ids) {
/* 50 */           if (OreDictionary.getOreName(id).equals(entry)) {
/* 51 */             synchronized (this.cache) { this.cache.put(Integer.valueOf(hid), Boolean.valueOf(true)); }
/* 52 */              return true;
/*    */           } 
/*    */         } 
/*    */       } 
/* 56 */       synchronized (this.cache) { this.cache.put(Integer.valueOf(hid), Boolean.valueOf(false)); }
/*    */     
/*    */     } 
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String getResearchKey(EntityPlayer player, Object object) { return this.research; }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\research\ScanOreDictionary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */