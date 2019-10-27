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
/*    */ import thaumcraft.api.capabilities.IPlayerKnowledge;
/*    */ import thaumcraft.api.capabilities.ThaumcraftCapabilities;
/*    */ import thaumcraft.api.research.ResearchCategories;
/*    */ import thaumcraft.api.research.ResearchEntry;
/*    */ import thaumcraft.client.gui.ResearchToast;
/*    */ import thaumcraft.common.lib.utils.Utils;
/*    */ 
/*    */ public class PacketSyncKnowledge extends Object implements IMessage, IMessageHandler<PacketSyncKnowledge, IMessage> {
/*    */   protected NBTTagCompound data;
/*    */   
/*    */   public PacketSyncKnowledge() {}
/*    */   
/*    */   public PacketSyncKnowledge(EntityPlayer player) {
/* 26 */     IPlayerKnowledge pk = ThaumcraftCapabilities.getKnowledge(player);
/* 27 */     this.data = (NBTTagCompound)pk.serializeNBT();
/* 28 */     for (String key : pk.getResearchList()) {
/* 29 */       pk.clearResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.POPUP);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void toBytes(ByteBuf buffer) { Utils.writeNBTTagCompoundToBuffer(buffer, this.data); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void fromBytes(ByteBuf buffer) { this.data = Utils.readNBTTagCompoundFromBuffer(buffer); }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IMessage onMessage(final PacketSyncKnowledge message, MessageContext ctx) {
/* 48 */     Minecraft.func_71410_x().func_152344_a(new Runnable() {
/*    */           public void run() {
/* 50 */             EntityPlayerSP entityPlayerSP = (Minecraft.func_71410_x()).field_71439_g;
/* 51 */             IPlayerKnowledge pk = ThaumcraftCapabilities.getKnowledge(entityPlayerSP);
/* 52 */             pk.deserializeNBT(this.val$message.data);
/* 53 */             for (String key : pk.getResearchList()) {
/* 54 */               if (pk.hasResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.POPUP)) {
/* 55 */                 ResearchEntry ri = ResearchCategories.getResearch(key);
/* 56 */                 if (ri != null) {
/* 57 */                   Minecraft.func_71410_x().func_193033_an().func_192988_a(new ResearchToast(ri));
/*    */                 }
/*    */               } 
/* 60 */               pk.clearResearchFlag(key, IPlayerKnowledge.EnumResearchFlag.POPUP);
/*    */             } 
/*    */           }
/*    */         });
/*    */     
/* 65 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\工程开发\Cracked\Thaumcraft-1.12.2-6.1.BETA26.jar!\thaumcraft\common\lib\network\playerdata\PacketSyncKnowledge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.7
 */