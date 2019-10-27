/*    */ package thaumcraft.common.lib.network.playerdata;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.EntityPlayerSP;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.api.capabilities.IPlayerWarp;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketSyncWarp extends Object implements IMessage, IMessageHandler<PacketSyncWarp, IMessage> {
/*    */   protected NBTTagCompound data;
/*    */   
/*    */   public PacketSyncWarp(EntityPlayer player) {
/* 21 */     IPlayerWarp pk = ThaumcraftCapabilities.getWarp(player);
/* 22 */     this.data = (NBTTagCompound)pk.serializeNBT();
/*    */   }
/*    */ 
/*    */   
/*    */   public PacketSyncWarp() {}
/*    */ 
/*    */   
/* 29 */   public void toBytes(ByteBuf buffer) { Utils.writeNBTTagCompoundToBuffer(buffer, this.data); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void fromBytes(ByteBuf buffer) { this.data = Utils.readNBTTagCompoundFromBuffer(buffer); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketSyncWarp message, MessageContext ctx) {
/* 40 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/* 41 */           public void run() { EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 42 */             IPlayerWarp pk = ThaumcraftCapabilities.getWarp(entityPlayerSP);
/* 43 */             pk.deserializeNBT(this.val$message.data); }
/*    */         });
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketSyncWarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */