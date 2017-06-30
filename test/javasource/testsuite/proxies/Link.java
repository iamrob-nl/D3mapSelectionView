// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package testsuite.proxies;

public class Link
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject linkMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "TestSuite.Link";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		source("source"),
		target("target"),
		Link_Links("TestSuite.Link_Links");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public Link(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "TestSuite.Link"));
	}

	protected Link(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject linkMendixObject)
	{
		if (linkMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("TestSuite.Link", linkMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a TestSuite.Link");

		this.linkMendixObject = linkMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Link.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static testsuite.proxies.Link initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return testsuite.proxies.Link.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static testsuite.proxies.Link initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new testsuite.proxies.Link(context, mendixObject);
	}

	public static testsuite.proxies.Link load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return testsuite.proxies.Link.initialize(context, mendixObject);
	}

	public static java.util.List<testsuite.proxies.Link> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<testsuite.proxies.Link> result = new java.util.ArrayList<testsuite.proxies.Link>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//TestSuite.Link" + xpathConstraint))
			result.add(testsuite.proxies.Link.initialize(context, obj));
		return result;
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of source
	 */
	public final java.lang.String getsource()
	{
		return getsource(getContext());
	}

	/**
	 * @param context
	 * @return value of source
	 */
	public final java.lang.String getsource(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.source.toString());
	}

	/**
	 * Set value of source
	 * @param source
	 */
	public final void setsource(java.lang.String source)
	{
		setsource(getContext(), source);
	}

	/**
	 * Set value of source
	 * @param context
	 * @param source
	 */
	public final void setsource(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String source)
	{
		getMendixObject().setValue(context, MemberNames.source.toString(), source);
	}

	/**
	 * @return value of target
	 */
	public final java.lang.String gettarget()
	{
		return gettarget(getContext());
	}

	/**
	 * @param context
	 * @return value of target
	 */
	public final java.lang.String gettarget(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.target.toString());
	}

	/**
	 * Set value of target
	 * @param target
	 */
	public final void settarget(java.lang.String target)
	{
		settarget(getContext(), target);
	}

	/**
	 * Set value of target
	 * @param context
	 * @param target
	 */
	public final void settarget(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String target)
	{
		getMendixObject().setValue(context, MemberNames.target.toString(), target);
	}

	/**
	 * @return value of Link_Links
	 */
	public final testsuite.proxies.Links getLink_Links() throws com.mendix.core.CoreException
	{
		return getLink_Links(getContext());
	}

	/**
	 * @param context
	 * @return value of Link_Links
	 */
	public final testsuite.proxies.Links getLink_Links(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		testsuite.proxies.Links result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Link_Links.toString());
		if (identifier != null)
			result = testsuite.proxies.Links.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Link_Links
	 * @param link_links
	 */
	public final void setLink_Links(testsuite.proxies.Links link_links)
	{
		setLink_Links(getContext(), link_links);
	}

	/**
	 * Set value of Link_Links
	 * @param context
	 * @param link_links
	 */
	public final void setLink_Links(com.mendix.systemwideinterfaces.core.IContext context, testsuite.proxies.Links link_links)
	{
		if (link_links == null)
			getMendixObject().setValue(context, MemberNames.Link_Links.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Link_Links.toString(), link_links.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return linkMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final testsuite.proxies.Link that = (testsuite.proxies.Link) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "TestSuite.Link";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}