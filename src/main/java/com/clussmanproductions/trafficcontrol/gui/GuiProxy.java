package com.clussmanproductions.trafficcontrol.gui;

import com.clussmanproductions.trafficcontrol.ModItems;
import com.clussmanproductions.trafficcontrol.tileentity.CrossingGateGateTileEntity;
import com.clussmanproductions.trafficcontrol.tileentity.SignTileEntity;
import com.clussmanproductions.trafficcontrol.tileentity.TrafficLightControlBoxTileEntity;
import com.clussmanproductions.trafficcontrol.tileentity.Type3BarrierTileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID)
		{
			case GUI_IDs.TRAFFIC_LIGHT_FRAME:
				if (player.getHeldItemMainhand().getItem() == ModItems.traffic_light_frame)
				{
					return new TrafficLightFrameContainer(player.inventory, player.getHeldItemMainhand());
				}
				break;
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		switch(ID)
		{
			case GUI_IDs.SIGN:
				BlockPos pos = new BlockPos(x, y, z);
				TileEntity te = world.getTileEntity(pos);
				if (te instanceof SignTileEntity)
				{
					SignTileEntity signTE = (SignTileEntity)te;
					return new SignGui(signTE, pos);
				}
				break;
			case GUI_IDs.TRAFFIC_LIGHT_FRAME:
				if (player.getHeldItemMainhand().getItem() == ModItems.traffic_light_frame)
				{
					return new TrafficLightFrameGui(player.inventory, player.getHeldItemMainhand());
				}
				break;
			case GUI_IDs.TRAFFIC_LIGHT_CONTROL_BOX:
				BlockPos preControlBoxPos = new BlockPos(x, y, z);
				TileEntity preControlBoxTE = world.getTileEntity(preControlBoxPos);
				if (preControlBoxTE instanceof TrafficLightControlBoxTileEntity)
				{
					TrafficLightControlBoxTileEntity controlBoxTE = (TrafficLightControlBoxTileEntity)preControlBoxTE;
					return new TrafficLightControlBoxGui(controlBoxTE);
				}
				break;
			case GUI_IDs.TYPE_3_BARRIER:
				BlockPos type3BarrierPos = new BlockPos(x, y, z);
				TileEntity type3BarrierTE = world.getTileEntity(type3BarrierPos);
				if (type3BarrierTE instanceof Type3BarrierTileEntity)
				{
					Type3BarrierTileEntity type3Barrier = (Type3BarrierTileEntity)type3BarrierTE;
					return new GuiType3Barrier(type3Barrier);
				}
				break;
			case GUI_IDs.CROSSING_GATE_GATE:
				BlockPos preCrossingGatePos = new BlockPos(x, y, z);
				TileEntity preCrossingGateTE = world.getTileEntity(preCrossingGatePos);
				if (preCrossingGateTE instanceof CrossingGateGateTileEntity)
				{
					CrossingGateGateTileEntity crossingGateTE = (CrossingGateGateTileEntity)preCrossingGateTE;
					return new CrossingGateGateGui(crossingGateTE);
				}
				break;
		}

		return null;
	}

	public static class GUI_IDs
	{
		public static final int SIGN = 1;
		public static final int TRAFFIC_LIGHT_FRAME = 2;
		public static final int TRAFFIC_LIGHT_CONTROL_BOX = 3;
		public static final int TYPE_3_BARRIER = 4;
		public static final int CROSSING_GATE_GATE = 5;
	}
}
