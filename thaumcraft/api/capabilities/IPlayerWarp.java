/*    */ package thaumcraft.api.capabilities;
/*    */ 
/*    */ import javax.annotation.Nonnull;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.common.util.INBTSerializable;
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
/*    */ 
/*    */ 
/*    */ public interface IPlayerWarp
/*    */   extends INBTSerializable<NBTTagCompound>
/*    */ {
/*    */   void clear();
/*    */   
/*    */   int get(@Nonnull EnumWarpType paramEnumWarpType);
/*    */   
/*    */   void set(@Nonnull EnumWarpType paramEnumWarpType, int paramInt);
/*    */   
/*    */   int add(@Nonnull EnumWarpType paramEnumWarpType, int paramInt);
/*    */   
/*    */   int reduce(@Nonnull EnumWarpType paramEnumWarpType, int paramInt);
/*    */   
/*    */   void sync(EntityPlayerMP paramEntityPlayerMP);
/*    */   
/*    */   int getCounter();
/*    */   
/*    */   void setCounter(int paramInt);
/*    */   
/*    */   public enum EnumWarpType
/*    */   {
/* 54 */     PERMANENT, NORMAL, TEMPORARY;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\api\capabilities\IPlayerWarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */