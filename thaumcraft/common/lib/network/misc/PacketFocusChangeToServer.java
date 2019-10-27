/*    */ package thaumcraft.common.lib.network.misc;
/*    */ 
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.world.WorldServer;
/*    */ import net.minecraftforge.fml.common.network.ByteBufUtils;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
/*    */ import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
/*    */ import thaumcraft.common.items.casters.CasterManager;
/*    */ 
/*    */ 
/*    */ public class PacketFocusChangeToServer
/*    */   extends Object
/*    */   implements IMessage, IMessageHandler<PacketFocusChangeToServer, IMessage>
/*    */ {
/*    */   private String focus;
/*    */   
/*    */   public PacketFocusChangeToServer() {}
/*    */   
/* 22 */   public PacketFocusChangeToServer(String focus) { this.focus = focus; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void toBytes(ByteBuf buffer) { ByteBufUtils.writeUTF8String(buffer, this.focus); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 32 */   public void fromBytes(ByteBuf buffer) { this.focus = ByteBufUtils.readUTF8String(buffer); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IMessage onMessage(final PacketFocusChangeToServer message, final MessageContext ctx) {
/* 38 */     WorldServer worldServer = (ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 39 */     worldServer.func_152344_a(new Runnable() {
/*    */           public void run() {
/* 41 */             WorldServer worldServer = (this.val$ctx.getServerHandler()).field_147369_b.func_71121_q();
/* 42 */             if (worldServer == null)
/*    */               return; 
/* 44 */             EntityPlayerMP entityPlayerMP = (this.val$ctx.getServerHandler()).field_147369_b;
/*    */             
/* 46 */             if (entityPlayerMP != null && entityPlayerMP instanceof EntityPlayer && ((EntityPlayer)entityPlayerMP)
/* 47 */               .func_184614_ca().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/* 48 */               CasterManager.changeFocus(((EntityPlayer)entityPlayerMP).func_184614_ca(), worldServer, (EntityPlayer)entityPlayerMP, message.focus);
/*    */             }
/* 50 */             else if (entityPlayerMP != null && entityPlayerMP instanceof EntityPlayer && ((EntityPlayer)entityPlayerMP)
/* 51 */               .func_184592_cb().func_77973_b() instanceof thaumcraft.api.casters.ICaster) {
/* 52 */               CasterManager.changeFocus(((EntityPlayer)entityPlayerMP).func_184592_cb(), worldServer, (EntityPlayer)entityPlayerMP, message.focus);
/*    */             } 
/*    */           }
/*    */         });
/*    */     
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\misc\PacketFocusChangeToServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */