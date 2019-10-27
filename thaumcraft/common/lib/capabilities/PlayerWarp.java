/*     */ package thaumcraft.common.lib.capabilities;
/*     */ 
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.capabilities.Capability;
/*     */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*     */ import net.minecraftforge.common.capabilities.ICapabilitySerializable;
/*     */ import thaumcraft.api.capabilities.IPlayerWarp;
/*     */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerWarp
/*     */ {
/*     */   public static void preInit() {
/*  25 */     CapabilityManager.INSTANCE.register(IPlayerWarp.class, new Capability.IStorage<IPlayerWarp>()
/*     */         {
/*     */           public NBTTagCompound writeNBT(Capability<IPlayerWarp> capability, IPlayerWarp instance, EnumFacing side) {
/*  28 */             return (NBTTagCompound)instance.serializeNBT();
/*     */           }
/*     */ 
/*     */           
/*     */           public void readNBT(Capability<IPlayerWarp> capability, IPlayerWarp instance, EnumFacing side, NBTBase nbt) {
/*  33 */             if (nbt instanceof NBTTagCompound) {
/*  34 */               instance.deserializeNBT((NBTTagCompound)nbt);
/*     */             }
/*     */           }
/*  37 */         },  () -> new DefaultImpl(null));
/*     */   }
/*     */   
/*     */   private static class DefaultImpl
/*     */     implements IPlayerWarp
/*     */   {
/*  43 */     private int[] warp = new int[IPlayerWarp.EnumWarpType.values().length];
/*     */ 
/*     */     
/*     */     private int counter;
/*     */ 
/*     */     
/*     */     public void clear() {
/*  50 */       this.warp = new int[IPlayerWarp.EnumWarpType.values().length];
/*  51 */       this.counter = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  56 */     public int get(@Nonnull IPlayerWarp.EnumWarpType type) { return this.warp[type.ordinal()]; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     public void set(IPlayerWarp.EnumWarpType type, int amount) { this.warp[type.ordinal()] = MathHelper.func_76125_a(amount, 0, 500); }
/*     */ 
/*     */ 
/*     */     
/*     */     public int add(@Nonnull IPlayerWarp.EnumWarpType type, int amount) {
/*  66 */       this.warp[type.ordinal()] = MathHelper.func_76125_a(this.warp[type.ordinal()] + amount, 0, 500);
/*  67 */       return this.warp[type.ordinal()];
/*     */     }
/*     */ 
/*     */     
/*     */     public int reduce(@Nonnull IPlayerWarp.EnumWarpType type, int amount) {
/*  72 */       this.warp[type.ordinal()] = MathHelper.func_76125_a(this.warp[type.ordinal()] - amount, 0, 500);
/*  73 */       return this.warp[type.ordinal()];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     public void sync(@Nonnull EntityPlayerMP player) { PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player), player); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public NBTTagCompound serializeNBT() {
/*  85 */       NBTTagCompound properties = new NBTTagCompound();
/*  86 */       properties.func_74783_a("warp", this.warp);
/*  87 */       properties.func_74768_a("counter", getCounter());
/*  88 */       return properties;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void deserializeNBT(NBTTagCompound properties) {
/*  94 */       if (properties == null)
/*  95 */         return;  clear();
/*  96 */       int[] ba = properties.func_74759_k("warp");
/*  97 */       if (ba != null) {
/*  98 */         int l = IPlayerWarp.EnumWarpType.values().length;
/*  99 */         if (ba.length < l) {
/* 100 */           l = ba.length;
/*     */         }
/* 102 */         for (int a = 0; a < l; a++) {
/* 103 */           this.warp[a] = ba[a];
/*     */         }
/*     */       } 
/* 106 */       setCounter(properties.func_74762_e("counter"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 111 */     public int getCounter() { return this.counter; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     public void setCounter(int amount) { this.counter = amount; }
/*     */     
/*     */     private DefaultImpl() {}
/*     */   }
/*     */   
/*     */   public static class Provider
/*     */     extends Object
/*     */     implements ICapabilitySerializable<NBTTagCompound>
/*     */   {
/* 125 */     public static final ResourceLocation NAME = new ResourceLocation("thaumcraft", "warp");
/*     */     
/* 127 */     private final PlayerWarp.DefaultImpl warp = new PlayerWarp.DefaultImpl(null);
/*     */ 
/*     */ 
/*     */     
/* 131 */     public boolean hasCapability(Capability<?> capability, EnumFacing facing) { return (capability == ThaumcraftCapabilities.WARP); }
/*     */ 
/*     */ 
/*     */     
/*     */     public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
/* 136 */       if (capability == ThaumcraftCapabilities.WARP)
/*     */       {
/* 138 */         return (T)ThaumcraftCapabilities.WARP.cast(this.warp);
/*     */       }
/* 140 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     public NBTTagCompound serializeNBT() { return this.warp.serializeNBT(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     public void deserializeNBT(NBTTagCompound nbt) { this.warp.deserializeNBT(nbt); }
/*     */   }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\capabilities\PlayerWarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */