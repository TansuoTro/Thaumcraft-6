/*     */ package thaumcraft.common.tiles;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.state.IBlockState;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SPacketUpdateTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.EnumFacing;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.fml.common.network.NetworkRegistry;
/*     */ import thaumcraft.common.lib.network.PacketHandler;
/*     */ import thaumcraft.common.lib.network.tiles.PacketTileToClient;
/*     */ import thaumcraft.common.lib.network.tiles.PacketTileToServer;
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
/*     */ public class TileThaumcraft
/*     */   extends TileEntity
/*     */ {
/*     */   public final void sendMessageToClient(NBTTagCompound nbt, @Nullable EntityPlayerMP player) {
/*  31 */     if (player == null) {
/*  32 */       if (func_145831_w() != null)
/*  33 */         PacketHandler.INSTANCE.sendToAllAround(new PacketTileToClient(
/*  34 */               func_174877_v(), nbt), new NetworkRegistry.TargetPoint(
/*  35 */               (func_145831_w()).field_73011_w.getDimension(), this.field_174879_c.func_177958_n() + 0.5D, this.field_174879_c.func_177956_o() + 0.5D, this.field_174879_c.func_177952_p() + 0.5D, 128.0D)); 
/*     */     } else {
/*  37 */       PacketHandler.INSTANCE.sendTo(new PacketTileToClient(func_174877_v(), nbt), player);
/*     */     } 
/*     */   }
/*     */   
/*  41 */   public final void sendMessageToServer(NBTTagCompound nbt) { PacketHandler.INSTANCE.sendToServer(new PacketTileToServer(func_174877_v(), nbt)); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void messageFromServer(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void messageFromClient(NBTTagCompound nbt, EntityPlayerMP player) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/*  54 */     super.func_145839_a(nbt);
/*  55 */     readSyncNBT(nbt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readSyncNBT(NBTTagCompound nbt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public NBTTagCompound func_189515_b(NBTTagCompound nbt) { return writeSyncNBT(super.func_189515_b(nbt)); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public NBTTagCompound writeSyncNBT(NBTTagCompound nbt) { return nbt; }
/*     */ 
/*     */   
/*     */   public void syncTile(boolean rerender) {
/*  75 */     IBlockState state = this.field_145850_b.func_180495_p(this.field_174879_c);
/*  76 */     this.field_145850_b.func_184138_a(this.field_174879_c, state, state, 2 + (rerender ? 4 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*  84 */   public SPacketUpdateTileEntity func_189518_D_() { return new SPacketUpdateTileEntity(this.field_174879_c, -9, func_189517_E_()); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) { readSyncNBT(pkt.func_148857_g()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public NBTTagCompound func_189517_E_() { return writeSyncNBT(setupNbt()); }
/*     */ 
/*     */   
/*     */   private NBTTagCompound setupNbt() {
/*  99 */     NBTTagCompound nbt = super.func_189515_b(new NBTTagCompound());
/* 100 */     nbt.func_82580_o("ForgeData");
/* 101 */     nbt.func_82580_o("ForgeCaps");
/* 102 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) { return (oldState.func_177230_c() != newState.func_177230_c()); }
/*     */ 
/*     */   
/*     */   public EnumFacing getFacing() {
/*     */     try {
/* 112 */       return EnumFacing.func_82600_a(func_145832_p() & 0x7);
/* 113 */     } catch (Exception exception) {
/* 114 */       return EnumFacing.UP;
/*     */     } 
/*     */   }
/*     */   
/* 118 */   public boolean gettingPower() { return this.field_145850_b.func_175640_z(func_174877_v()); }
/*     */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\tiles\TileThaumcraft.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */