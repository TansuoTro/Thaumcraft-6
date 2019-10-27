/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketItemToClientContainer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketItemToClientContainer, IMessage> {
/*    */   private int windowId;
/*    */   private int slot;
/*    */   private ItemStack item;
/*    */   
/*    */   public PacketItemToClientContainer() {}
/*    */   
/*    */   public PacketItemToClientContainer(int windowIdIn, int slotIn, ItemStack itemIn) {
/* 21 */     this.windowId = windowIdIn;
/* 22 */     this.slot = slotIn;
/* 23 */     this.item = itemIn;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toBytes(ByteBuf dos) {
/* 28 */     dos.writeInt(this.windowId);
/* 29 */     dos.writeInt(this.slot);
/* 30 */     Utils.writeItemStackToBuffer(dos, this.item);
/*    */   }
/*    */ 
/*    */   
/*    */   public void fromBytes(ByteBuf dat) {
/* 35 */     this.windowId = dat.readInt();
/* 36 */     this.slot = dat.readInt();
/* 37 */     this.item = Utils.readItemStackFromBuffer(dat);
/*    */   }
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketItemToClientContainer message, MessageContext ctx) {
/* 42 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/*    */           public void run() { try {
/* 44 */               if ((Minecraft.func_71410_x()).field_71439_g.field_71070_bA != null && (Minecraft.func_71410_x()).field_71439_g.field_71070_bA.field_75152_c == message.windowId) {
/* 45 */                 (Minecraft.func_71410_x()).field_71439_g.field_71070_bA.func_75141_a(message.slot, message.item);
/*    */               }
/* 47 */             } catch (Exception e) {
/* 48 */               e.printStackTrace();
/*    */             }  }
/*    */         });
/* 51 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketItemToClientContainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */