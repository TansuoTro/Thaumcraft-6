/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.biome.Biome;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import net.minecraftforge.fml.relauncher.Side;
/*    */ import net.minecraftforge.fml.relauncher.SideOnly;
/*    */ import thaumcraft.Thaumcraft;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketBiomeChange extends Object implements IMessage, IMessageHandler<PacketBiomeChange, IMessage> {
/*    */   private int x;
/*    */   private int z;
/*    */   private short biome;
/*    */   
/*    */   public PacketBiomeChange() {}
/*    */   
/*    */   public PacketBiomeChange(int x, int z, short biome) {
/* 23 */     this.x = x;
/* 24 */     this.z = z;
/* 25 */     this.biome = biome;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf buffer) {
/* 30 */     buffer.writeInt(this.x);
/* 31 */     buffer.writeInt(this.z);
/* 32 */     buffer.writeShort(this.biome);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf buffer) {
/* 37 */     this.x = buffer.readInt();
/* 38 */     this.z = buffer.readInt();
/* 39 */     this.biome = buffer.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketBiomeChange message, MessageContext ctx) {
/* 45 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/* 46 */           public void run() { Utils.setBiomeAt(Thaumcraft.proxy.getClientWorld(), new BlockPos(message.x, 0, message.z), Biome.func_150568_d(message.biome)); }
/*    */         });
/* 48 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketBiomeChange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */