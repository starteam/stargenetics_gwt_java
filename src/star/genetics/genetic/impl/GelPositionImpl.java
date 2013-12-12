package star.genetics.genetic.impl;

import java.io.Serializable;

import star.genetics.genetic.model.Allele;
import star.genetics.genetic.model.Gel;
import star.genetics.genetic.model.GelPosition;

public class GelPositionImpl implements GelPosition, Serializable
{
	private static final long serialVersionUID = 1L;
	private final Gel gel;
	private final Float[] position;
	private final Allele allele;

	public GelPositionImpl(Gel gel, Float[] position, Allele allele)
	{
		this.gel = gel;
		this.position = position;
		this.allele = allele;
	}

	@Override
	public Gel getGel()
	{
		return gel;
	}

	@Override
	public Float[] getPosition()
	{
		return position;
	}

	@Override
	public Allele getAllele()
	{
		return allele;
	}

}
