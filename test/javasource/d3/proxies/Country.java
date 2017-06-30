// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package d3.proxies;

public class Country
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject countryMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "D3.Country";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		_id("_id"),
		Name("Name"),
		Orders("Orders"),
		JsonObject_Root("D3.JsonObject_Root"),
		Item_JsonObject("D3.Item_JsonObject");

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

	public Country(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "D3.Country"));
	}

	protected Country(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject countryMendixObject)
	{
		if (countryMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("D3.Country", countryMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a D3.Country");

		this.countryMendixObject = countryMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Country.load(IContext, IMendixIdentifier)' instead.
	 */
	@Deprecated
	public static d3.proxies.Country initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return d3.proxies.Country.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static d3.proxies.Country initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new d3.proxies.Country(context, mendixObject);
	}

	public static d3.proxies.Country load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return d3.proxies.Country.initialize(context, mendixObject);
	}

	public static java.util.List<d3.proxies.Country> load(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String xpathConstraint) throws com.mendix.core.CoreException
	{
		java.util.List<d3.proxies.Country> result = new java.util.ArrayList<d3.proxies.Country>();
		for (com.mendix.systemwideinterfaces.core.IMendixObject obj : com.mendix.core.Core.retrieveXPathQuery(context, "//D3.Country" + xpathConstraint))
			result.add(d3.proxies.Country.initialize(context, obj));
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
	 * @return value of _id
	 */
	public final java.lang.String get_id()
	{
		return get_id(getContext());
	}

	/**
	 * @param context
	 * @return value of _id
	 */
	public final java.lang.String get_id(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames._id.toString());
	}

	/**
	 * Set value of _id
	 * @param _id
	 */
	public final void set_id(java.lang.String _id)
	{
		set_id(getContext(), _id);
	}

	/**
	 * Set value of _id
	 * @param context
	 * @param _id
	 */
	public final void set_id(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String _id)
	{
		getMendixObject().setValue(context, MemberNames._id.toString(), _id);
	}

	/**
	 * @return value of Name
	 */
	public final java.lang.String getName()
	{
		return getName(getContext());
	}

	/**
	 * @param context
	 * @return value of Name
	 */
	public final java.lang.String getName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Name.toString());
	}

	/**
	 * Set value of Name
	 * @param name
	 */
	public final void setName(java.lang.String name)
	{
		setName(getContext(), name);
	}

	/**
	 * Set value of Name
	 * @param context
	 * @param name
	 */
	public final void setName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String name)
	{
		getMendixObject().setValue(context, MemberNames.Name.toString(), name);
	}

	/**
	 * @return value of Orders
	 */
	public final java.lang.Integer getOrders()
	{
		return getOrders(getContext());
	}

	/**
	 * @param context
	 * @return value of Orders
	 */
	public final java.lang.Integer getOrders(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Integer) getMendixObject().getValue(context, MemberNames.Orders.toString());
	}

	/**
	 * Set value of Orders
	 * @param orders
	 */
	public final void setOrders(java.lang.Integer orders)
	{
		setOrders(getContext(), orders);
	}

	/**
	 * Set value of Orders
	 * @param context
	 * @param orders
	 */
	public final void setOrders(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Integer orders)
	{
		getMendixObject().setValue(context, MemberNames.Orders.toString(), orders);
	}

	/**
	 * @return value of JsonObject_Root
	 */
	public final d3.proxies.Root getJsonObject_Root() throws com.mendix.core.CoreException
	{
		return getJsonObject_Root(getContext());
	}

	/**
	 * @param context
	 * @return value of JsonObject_Root
	 */
	public final d3.proxies.Root getJsonObject_Root(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		d3.proxies.Root result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.JsonObject_Root.toString());
		if (identifier != null)
			result = d3.proxies.Root.load(context, identifier);
		return result;
	}

	/**
	 * Set value of JsonObject_Root
	 * @param jsonobject_root
	 */
	public final void setJsonObject_Root(d3.proxies.Root jsonobject_root)
	{
		setJsonObject_Root(getContext(), jsonobject_root);
	}

	/**
	 * Set value of JsonObject_Root
	 * @param context
	 * @param jsonobject_root
	 */
	public final void setJsonObject_Root(com.mendix.systemwideinterfaces.core.IContext context, d3.proxies.Root jsonobject_root)
	{
		if (jsonobject_root == null)
			getMendixObject().setValue(context, MemberNames.JsonObject_Root.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.JsonObject_Root.toString(), jsonobject_root.getMendixObject().getId());
	}

	/**
	 * @return value of Item_JsonObject
	 */
	public final d3.proxies.Items getItem_JsonObject() throws com.mendix.core.CoreException
	{
		return getItem_JsonObject(getContext());
	}

	/**
	 * @param context
	 * @return value of Item_JsonObject
	 */
	public final d3.proxies.Items getItem_JsonObject(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		d3.proxies.Items result = null;
		com.mendix.systemwideinterfaces.core.IMendixIdentifier identifier = getMendixObject().getValue(context, MemberNames.Item_JsonObject.toString());
		if (identifier != null)
			result = d3.proxies.Items.load(context, identifier);
		return result;
	}

	/**
	 * Set value of Item_JsonObject
	 * @param item_jsonobject
	 */
	public final void setItem_JsonObject(d3.proxies.Items item_jsonobject)
	{
		setItem_JsonObject(getContext(), item_jsonobject);
	}

	/**
	 * Set value of Item_JsonObject
	 * @param context
	 * @param item_jsonobject
	 */
	public final void setItem_JsonObject(com.mendix.systemwideinterfaces.core.IContext context, d3.proxies.Items item_jsonobject)
	{
		if (item_jsonobject == null)
			getMendixObject().setValue(context, MemberNames.Item_JsonObject.toString(), null);
		else
			getMendixObject().setValue(context, MemberNames.Item_JsonObject.toString(), item_jsonobject.getMendixObject().getId());
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return countryMendixObject;
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
			final d3.proxies.Country that = (d3.proxies.Country) obj;
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
		return "D3.Country";
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